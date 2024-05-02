package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.dto.MarketCheckData;

public interface MarketCheckApiService {
    /**
     * This method sends marketCheck data
     * @param vin This is vin number
     * @return MarketCheckData object
     * @author Rupesh
     */
    MarketCheckData getMarketCheckData(String vin) throws AppraisalException;

}
