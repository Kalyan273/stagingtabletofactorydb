package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.Company;


public interface CompanyDetailsService {

    /**
     * this method will create company details
     * @param compDetails This is CompDetails dto  coming from ui
     * Method taking Company details as argument and returning a message
     * @author yudhister
     * @return
     */
    String addCompany(Company compDetails);

    /**
     * this method will update company details by  reference compId
     * @param compDetails This is CompDetails dto  coming from ui
     * @param compId This is company id coming from ui in header
     * @author yudhister
     * @return
     */
    public Response updateCompanyDetails(Company compDetails, Long compId) throws AppraisalException;


}
