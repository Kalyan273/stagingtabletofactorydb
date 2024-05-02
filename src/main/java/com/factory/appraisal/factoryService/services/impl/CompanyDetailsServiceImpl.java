package com.factory.appraisal.factoryService.services.impl;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.Company;
import com.factory.appraisal.factoryService.persistence.mapper.AppraisalVehicleMapper;

import com.factory.appraisal.factoryService.persistence.model.*;
import com.factory.appraisal.factoryService.repository.CompanyRepo;
import com.factory.appraisal.factoryService.services.CompanyDetailsService;
import com.factory.appraisal.factoryService.util.CompareUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {
    Logger log = LoggerFactory.getLogger(CompanyDetailsServiceImpl.class);

    @Autowired
    private CompanyRepo compDetailsRepo;

    @Autowired
    private AppraisalVehicleMapper appraisalVehicleMapper;
    @Autowired
    private CompareUtils compareUtils;

    @Override
    public String addCompany(Company compDetails) {
        ECompany eCompDetails= appraisalVehicleMapper.compDetsToECompDets(compDetails);
        eCompDetails.setValid(true);
        eCompDetails.setCreatedOn(new Date());
        compDetailsRepo.save(eCompDetails);
        return "Company details added successfully";
    }

    @Override
    public Response updateCompanyDetails(Company compDetails, Long compId) throws AppraisalException {

        ECompany company=compDetailsRepo.findByCompanyId(compId);

            if (null != company) {
                company=appraisalVehicleMapper.updateCompDetails(compDetails,company);

                compDetailsRepo.save(company);
            } else throw new AppraisalException("Invalid Company id Send..");


        Response response =new Response();
        response.setMessage("Updated Successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

}


