package com.factory.appraisal.factoryService.services.impl;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.dto.*;
import com.factory.appraisal.factoryService.persistence.mapper.AppraisalVehicleMapper;
import com.factory.appraisal.factoryService.persistence.mapper.OffersMapper;
import com.factory.appraisal.factoryService.persistence.model.*;
import com.factory.appraisal.factoryService.repository.*;
import com.factory.appraisal.factoryService.services.FilterSpecificationService;
import com.factory.appraisal.factoryService.util.AppraisalSpecification;
import com.factory.appraisal.factoryService.util.DealersUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilterSpecificationServiceImpl implements FilterSpecificationService {

    @Autowired
    private AppraiseVehicleRepo eAppraiseVehicleRepo;
    @Autowired
    private AppraisalVehicleMapper appraisalVehicleMapper;
    @Autowired
    private RoleMappingRepo roleMappingRepo;

    @Autowired
    private UserRegistrationRepo userRegistrationRepo;
    @Autowired
    private DlrInvntryViewRepo dlrInvntryViewRepo;
    @Autowired
    private OffersMapper offersMapper;

    @Autowired
    private TransReportRepo transReportRepo;
    @Autowired
    private DealersUser dealersUser;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private MembersByFactoryManagerRepo ftryManagerRepo;
    @Autowired
    private MembersByFactorySalesmenRepo ftrySalesmenRepo;
    @Autowired
    private DealerRegistrationRepo dlrRegRepo;


    @Autowired
    private UserListViewRepo userListViewRepo;



    Logger log = LoggerFactory.getLogger(FilterSpecificationServiceImpl.class);
    @Override
    public CardsPage filterAppraisalVehicle(FilterParameters filter, UUID userId, Integer pageNo, Integer pageSize) throws AppraisalException {
        Page<EAppraiseVehicle> resultPage=null;
        CardsPage pageInfo = new CardsPage();
        EUserRegistration userById = userRegistrationRepo.findUserById(userId);

            if (null != userById) {


                Pageable pageable = PageRequest.of(pageNo, pageSize);

                if (null!=filter) {
                    resultPage = eAppraiseVehicleRepo.findAll(AppraisalSpecification.getEApprSpecification(filter, userId), pageable);
                }else {
                    resultPage = eAppraiseVehicleRepo.appraisalCards(userId, true,pageable);
                }

                pageInfo.setTotalRecords(resultPage.getTotalElements());

                List<AppraisalVehicleCard> cards = appraisalVehicleMapper.lEApprVehiToLApprVehiCard(resultPage.getContent());
                pageInfo.setCards(cards);
                pageInfo.setTotalPages(resultPage.getTotalPages());
                pageInfo.setCode(HttpStatus.OK.value());
                pageInfo.setMessage("Appraisal vehicle cards are visible");
                pageInfo.setStatus(Boolean.TRUE);
                return pageInfo;
            } else throw new AppraisalException(AppraisalConstants.INALID_USER_ID);
    }
    public CardsPage filterInventoryVehicle(FilterParameters filter, UUID userId, Integer pageNo,Integer pageSize) throws AppraisalException {
        Page<EAppraiseVehicle>resultPage=null;
        CardsPage pageInfo = new CardsPage();
        EUserRegistration userById = userRegistrationRepo.findUserById(userId);

            if (null != userById) {

                Pageable pageable = PageRequest.of(pageNo, pageSize);

                if (null!=filter) {
                    resultPage = eAppraiseVehicleRepo.findAll(AppraisalSpecification.getInventrySpecification(filter, userId), pageable);
                }else {
                    resultPage = eAppraiseVehicleRepo.findUserAndInvntrySts(userId, AppraisalConstants.INVENTORY,true,pageable);
                }

                pageInfo.setTotalRecords(resultPage.getTotalElements());

                List<AppraisalVehicleCard> cards = appraisalVehicleMapper.lEApprVehiToLApprVehiCard(resultPage.getContent());
                pageInfo.setCards(cards);
                pageInfo.setTotalPages(resultPage.getTotalPages());
                pageInfo.setCode(HttpStatus.OK.value());
                pageInfo.setMessage("Inventory vehicle cards are visible");
                pageInfo.setStatus(Boolean.TRUE);
            } else throw new AppraisalException("userID is null");

        return pageInfo;
    }


    @Override
    public CardsPage filterSearchFactoryVehicle(FilterParameters filter, UUID userId, Integer pageNo, Integer pageSize) throws AppraisalException {
        Page<EAppraiseVehicle>resultPage=null;
        CardsPage pageInfo = new CardsPage();
        EUserRegistration userById = userRegistrationRepo.findUserById(userId);

            if (null != userById) {
                Pageable pageable = PageRequest.of(pageNo, pageSize);

                if (null!=filter) {
                    resultPage = eAppraiseVehicleRepo.findAll(AppraisalSpecification.getSearchFactorySpecification(filter,userId), pageable);
                }else {
                    resultPage = eAppraiseVehicleRepo.findByUserIdNot(userId,AppraisalConstants.INVENTORY, true,pageable);
                }

                pageInfo.setTotalRecords(resultPage.getTotalElements());

                List<AppraisalVehicleCard> cards = appraisalVehicleMapper.lEApprVehiToLApprVehiCard(resultPage.getContent());
                pageInfo.setCards(cards);
                pageInfo.setTotalPages(resultPage.getTotalPages());
                ERoleMapping byUserId = roleMappingRepo.findByUserId(userId);
                pageInfo.setRoleType(byUserId.getRole().getRole());
                pageInfo.setRoleGroup(byUserId.getRole().getRoleGroup());
                pageInfo.setCode(HttpStatus.OK.value());
                pageInfo.setMessage("SearchFactory vehicle cards are visible");
                pageInfo.setStatus(Boolean.TRUE);
            } else throw new AppraisalException("userID is null");
        return pageInfo;
    }


    @Override
    public SellingDealerList sendSlrDlrList(SellingDealer filter) throws AppraisalException {

        List<TransactionReport> all = transReportRepo.findAll( AppraisalSpecification.displayBySellingDealer(filter));
        SellingDealerList dealerList= new SellingDealerList();
        if(null!=all){
            List<SellingDealer> sellingDealers = appraisalVehicleMapper.lTransaRepToSellingDealer(all);
            dealerList.setSlrDlrList(sellingDealers);
        }else throw new AppraisalException("No such Dealer Found");
        dealerList.setCode(HttpStatus.OK.value());
        dealerList.setMessage("dealerList found");
        dealerList.setStatus(true);
        return dealerList;
    }

    @Override
    public SellingDealerList displayByFtryManagerList(SellingDealer filter) throws AppraisalException {
        List<MembersByFactoryManager> all = ftryManagerRepo.findAll(AppraisalSpecification.displayByFtryManager(filter));
        SellingDealerList dealerList= new SellingDealerList();
        if(null!=all){
            List<SellingDealer> sellingDealers = appraisalVehicleMapper.lMangerRepToSellingDealer(all);
            sellingDealers= sellingDealers.stream().distinct().collect(Collectors.toList());
            dealerList.setSlrDlrList(sellingDealers);
        }else throw new AppraisalException("No such Dealer Found");
        dealerList.setCode(HttpStatus.OK.value());
        dealerList.setMessage("dealerList found");
        dealerList.setStatus(true);
        return dealerList;
    }

    @Override
    public SellingDealerList displayByFtrySalesList(SellingDealer filter) throws AppraisalException {
        List<MembersByFactorySalesmen> all = ftrySalesmenRepo.findAll( AppraisalSpecification.displayByFtrySales(filter));
        SellingDealerList dealerList= new SellingDealerList();
        if(null!=all){
            List<SellingDealer> sellingDealers = appraisalVehicleMapper.lSaleToSellingDealer(all);
            sellingDealers= sellingDealers.stream().distinct().collect(Collectors.toList());
            dealerList.setSlrDlrList(sellingDealers);
        }else throw new AppraisalException("No such Dealer Found");
        dealerList.setCode(HttpStatus.OK.value());
        dealerList.setMessage("dealerList found");
        dealerList.setStatus(true);
        return dealerList;
    }

    @Override
    public DlrList srchByDlrList(DealerRegistration filter) throws AppraisalException {
        List<EDealerRegistration> all = dlrRegRepo.findAll( AppraisalSpecification.dsplyDlrList(filter));
        DlrList dealerList= new DlrList();
        if(null!=all){
            List<DealerRegistration> sellingDealers = appraisalVehicleMapper.eDlrRegToDlrReg(all);
            dealerList.setDlrWithNoCmp(sellingDealers);
        }else throw new AppraisalException("No such Dealer Found");
        dealerList.setCode(HttpStatus.OK.value());
        dealerList.setMessage("dealerList found");
        dealerList.setStatus(true);
        return dealerList;
    }


    @Override
    public TableList sendDlrFilterList(DlrInvntryPdfFilter filter, UUID userId, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<UUID> allUsersUnderDealer = dealersUser.getAllUsersUnderDealer(userId);
        filter.setUsers(allUsersUnderDealer);
        Specification<DlrInvntryView> dlrInvntryViewSpecification = AppraisalSpecification.getDlrInvntryViewSpecification(filter);
        Page<DlrInvntryView> all1 = dlrInvntryViewRepo.findAll(dlrInvntryViewSpecification, pageable);
        List<PdfDataDto> dlrInvntryFilter = offersMapper.lDlrInvViewToPdfDataDto(all1.toList());
        TableList tableList= new TableList();
        tableList.setDlrInvntryList(dlrInvntryFilter);
        tableList.setCode(HttpStatus.OK.value());
        tableList.setMessage("dealerList found");
        tableList.setStatus(true);
        tableList.setTotalPages(all1.getTotalPages());
        tableList.setTotalRecords(all1.getTotalElements());
        return  tableList;
    }

    @Override
    public List<Company> searchCompany(String name) {
        int pageSize = 1; // Number of items to return per page
        int startIndex = 0; // Calculate the start index for the current page

        List<Company> companies = null;
        List<ECompany> companyList = companyRepo.findAll(AppraisalSpecification.searchByCompany(name));

        if (companyList != null) {
            companies = appraisalVehicleMapper.leCompDetailsTolCompDetails(companyList);

            // Check if there are enough elements for the requested page
            if (startIndex < companies.size()) {
                int endIndex = Math.min(startIndex + pageSize, companies.size());
                companies = companies.subList(startIndex, endIndex);
            } else {
                // If there are no more elements for the requested page, return an empty list
                companies = Collections.emptyList();
            }
        }
        return companies;
    }

    @Override
    public TableList sendDlrFilterListPdf(DlrInvntryPdfFilter filter, UUID userId) {

        List<UUID> allUsersUnderDealer = dealersUser.getAllUsersUnderDealer(userId);
        filter.setUsers(allUsersUnderDealer);
        Specification<DlrInvntryView> dlrInvntryViewSpecification = AppraisalSpecification.getDlrInvntryViewSpecification(filter);
        List<DlrInvntryView> all1 = dlrInvntryViewRepo.findAll(dlrInvntryViewSpecification);
        List<PdfDataDto> dlrInvntryFilter = offersMapper.lDlrInvViewToPdfDataDto(all1);
        TableList tableList= new TableList();
        tableList.setDlrInvntryList(dlrInvntryFilter);
        tableList.setCode(HttpStatus.OK.value());
        tableList.setMessage("dealerList found");
        tableList.setStatus(true);
        return  tableList;
    }

}
