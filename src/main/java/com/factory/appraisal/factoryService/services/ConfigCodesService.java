package com.factory.appraisal.factoryService.services;
/**
 *
 */


import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.AppraisalConfigs;
import com.factory.appraisal.factoryService.dto.ConfigDropDown;
import com.factory.appraisal.factoryService.dto.ConfigDropDown;
import com.factory.appraisal.factoryService.dto.FilterDropdowns;

import java.util.List;
import java.util.UUID;


public interface ConfigCodesService {
    /**
     * This method inserts configCodes into the database
     * @param configCodes This is the object of List<ConfigCodes>
     * @return message
     */
    public String addConfigCode(List<ConfigDropDown> configCodes);

    /**
     *This method send the AppraisalConfigs dto to ui base on userId
     * @param userId This is the User id
     * @return AppraisalConfig
     */
    AppraisalConfigs getAppraisalConfigs(UUID userId) throws AppraisalException;

    /**
     * This method is used to update configCodes
     * @param configCodes
     * @param codeId
     * @return
     */
    Response updateConfig(ConfigDropDown configCodes,Long codeId) throws GlobalException;

    /**
     * This method is used ti delete the configCodes
     * @param codeId
     * @return
     */
    Response deleteConfig(Long codeId) throws GlobalException;

    FilterDropdowns sendFilterParams();
    void saveNewExtColor();
    void saveNewIntColor();


}
