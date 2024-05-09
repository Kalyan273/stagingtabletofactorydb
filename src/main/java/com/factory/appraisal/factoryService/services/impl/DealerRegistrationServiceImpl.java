package com.factory.appraisal.factoryService.services.impl;

/**
 * DealerRegistration method is taking the dealerRegistration object as input and creating user
 * @author Kalyan
 */


import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.config.AuditConfiguration;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.controller.DealerRegistrationController;
import com.factory.appraisal.factoryService.dto.*;
import com.factory.appraisal.factoryService.mktCheck.model.EMkDealerRegistration;
import com.factory.appraisal.factoryService.mktCheck.repo.MktDealerRepo;
import com.factory.appraisal.factoryService.persistence.mapper.AppraisalVehicleMapper;
import com.factory.appraisal.factoryService.persistence.model.*;
import com.factory.appraisal.factoryService.repository.*;
import com.factory.appraisal.factoryService.services.DealerRegistrationService;
import com.factory.appraisal.factoryService.services.UserRegistrationService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class DealerRegistrationServiceImpl implements DealerRegistrationService {

    Logger log = LoggerFactory.getLogger(DealerRegistrationController.class);

    @Autowired
    private DealerRegistrationRepo dlrRegRepo;
    @Value("${image_folder_path}")
    private String imageFolderPath;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRegistrationRepo userRegRepo;

    @Autowired
    private AppraisalVehicleMapper apprVehMapper;

    @Autowired
    private MktDealerRepo dealerRepo;

    @Autowired
    private RoleMappingRepo roleMapRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private UserRegistrationService userRegService;

    @Autowired
    private AuditConfiguration auditConfiguration;


    @Override
    @Transactional
    public String createDealer(DealerRegistration dealerRegistration) throws AppraisalException, MessagingException, TemplateException, IOException {
        log.info("This method is used to create Dealer");
        EDealerRegistration eDealerRegistration = apprVehMapper.dealerRegToEdealerReg(dealerRegistration);
        log.debug("Object coming for creating dealer {}", eDealerRegistration);
        eDealerRegistration.setCreatedOn(new Date());
        if(null!=dealerRegistration.getDealershipNames()){
            eDealerRegistration.setDealershipNames(dealerRegistration.getDealershipNames().toLowerCase());
            eDealerRegistration.setStatus(AppraisalConstants.PENDING);
        }
        ERole byRole = roleRepo.findByRole(dealerRegistration.getRoleId());

        if(byRole.getRole().equalsIgnoreCase("D2") || byRole.getRole().equalsIgnoreCase("D3") || byRole.getRole().equalsIgnoreCase("S1") || byRole.getRole().equalsIgnoreCase("M1")){
            eDealerRegistration.setStatus(null);
        }
        auditConfiguration.setAuditorName(eDealerRegistration.getName());
        EDealerRegistration save = dlrRegRepo.save(eDealerRegistration);

        log.info("Dealer is saved and Process started for user registration");
        if (byRole.getRole().equalsIgnoreCase("D2") || byRole.getRole().equalsIgnoreCase("D3") || byRole.getRole().equalsIgnoreCase("S1") || byRole.getRole().equalsIgnoreCase("M1")) {
            if(null!=dealerRegistration.getDealerAdmin()){
                ERoleMapping byUserId = roleMapRepo.findByUserId(dealerRegistration.getDealerAdmin());

                if(null!=byUserId.getManager()){
                    dealerRegistration.setManagerId(byUserId.getManager().getId());
                }
                if(null!=byUserId.getFactorySalesman()){
                    dealerRegistration.setFactorySalesman(byUserId.getFactorySalesman().getId());
                }
                if(null!=byUserId.getFactoryManager()){
                    dealerRegistration.setFactoryManager(byUserId.getFactoryManager().getId());
                }
            }
            UserRegistration userRegistration = apprVehMapper.dealerToUser(dealerRegistration);
            userRegService.createUser(userRegistration, save.getId());
        }
        return "Dealer Has Been saved Successfully";
    }
    @Override
    public DealerRegistration showInDealerEditPage(Long dealerId) throws AppraisalException {
            log.info("Showing Dealer in Edit page is triggered **Service IMPL**");
            EDealerRegistration dealer = dlrRegRepo.findDealerById(dealerId);
            if(null!=dealer){
            EUserRegistration user = userRegRepo.findByUserNameAndPassword(dealer.getName(), dealer.getPassword());
            UUID id = user.getId();

                DealerRegistration dealerRegistration = apprVehMapper.edealerRegToDealerReg(dealer);
                dealerRegistration.setUserId(id);
                log.debug("OBJECT FOR SHOWING DEALER IN EDIT PAGE **Service IMPL**");
                dealerRegistration.setCode(HttpStatus.OK.value());
                dealerRegistration.setMessage("Successfully showing in edit page");
                dealerRegistration.setStatus(true);
                return dealerRegistration;
            }else throw new AppraisalException("Invalid Dealer Id");

    }

    @Override
    public Response updateDealer(DealerRegistration newDealer, Long dealerId,UUID userId) throws AppraisalException, IOException {
        log.info("Dealer update method is triggered **Service IMPL**");
        EDealerRegistration dealer = dlrRegRepo.findDealerById(dealerId);
        EUserRegistration user = userRegRepo.findUserById(userId);

            if (null!=dealer){

                dealer = apprVehMapper.updEDlrReg(newDealer, dealer);

                if (Boolean.TRUE.equals(updatePics(newDealer.getDealerPic(), dealer.getDealerPic()))) {

                    dealer.setDealerPic(newDealer.getDealerPic());
                }
                if (Boolean.TRUE.equals(updatePics(newDealer.getTaxCertificate(), dealer.getTaxCertificate()))) {

                    dealer.setTaxCertificate(newDealer.getTaxCertificate());
                }
                if (Boolean.TRUE.equals(updatePics(newDealer.getDealerCert(), dealer.getDealerCert()))) {

                    dealer.setDealerCert(newDealer.getDealerCert());
                }

                EUserRegistration eUserReg = apprVehMapper.updateEUserReg(dealer,user);
                log.debug("Dealer object showing after updat {}",dealer);
                eUserReg.setModifiedOn(new Date());
                userRegRepo.save(eUserReg);
                dealer.setModifiedOn(new Date());
                dlrRegRepo.save(dealer);

            }else throw new AppraisalException("Invalid Dealer Id");

        Response response =new Response();
        response.setMessage("Updated Successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response deleteDealer(Long dealerId) throws GlobalException {
        Response response=new Response();
        EDealerRegistration dealerById = dlrRegRepo.findDealerById(dealerId);
        if (null!=dealerById){
            dealerById.setValid(Boolean.FALSE);
            EUserRegistration eUserRegistration = userRegRepo.checkUserNamePresent(dealerById.getName());
            eUserRegistration.setValid(Boolean.FALSE);
            response.setStatus(Boolean.TRUE);
            response.setMessage("dealer deleted successfully");
            response.setCode(HttpStatus.OK.value());
            userRegRepo.save(eUserRegistration);
            dlrRegRepo.save(dealerById);
        }else throw new GlobalException("Dealer not found");
        return response;
    }

    @Override
    public DlrList getDlrList(Integer pageNo, Integer pageSize) throws GlobalException,AppraisalException {
        log.info("Showing Dealer list of companyName is null is triggered **Service IMPL**");
        DlrList pageInfo=new DlrList();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(AppraisalConstants.CREATEDON).descending());
        Page<EDealerRegistration> pageResult = dlrRegRepo.getDlrListOfcompNameNull(pageable);
        if(pageResult.getTotalElements()!=0) {
            pageInfo.setTotalRecords(pageResult.getTotalElements());
            pageInfo.setTotalPages(pageResult.getTotalPages());
            List<DealerRegistration> dlrList = apprVehMapper.eDlrRegToDlrReg(pageResult.toList());
            pageInfo.setDlrWithNoCmp(dlrList);

        }
        else throw new AppraisalException("Dealer list not available");
        pageInfo.setStatus(Boolean.TRUE);
        pageInfo.setMessage("dealer list with no company successfully");
        pageInfo.setCode(HttpStatus.OK.value());

        return pageInfo;
    }

    @Override
    public Response setCompIdToDealer(Long dealerId, Long compId) {
        EDealerRegistration dealer = dlrRegRepo.findDealerById(dealerId);
        ECompany companyId = companyRepo.findByCompanyId(compId);
        dealer.setCompany(companyId);
        dlrRegRepo.save(dealer);
        Response response=new Response();
        response.setMessage("Company set Successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }


    /**
     * This method checks the old image and new image having same file name or not. If file name is not same then deleting the old image and returns
     * true else returns false
     * @param newPic This is the file name of new image
     * @param oldPic This is the file name of old image
     * @return Boolean
     */
    public Boolean updatePics(String newPic, String oldPic) throws IOException {

        if(null==newPic && null!=oldPic ||(null!=newPic && null==oldPic) || (null!=newPic && null !=oldPic && !newPic.equals(oldPic)) ){
                    Path filePath = Paths.get(imageFolderPath + oldPic);
                    Files.delete(filePath);
                    return true;
        }
        return false;
    }



}
