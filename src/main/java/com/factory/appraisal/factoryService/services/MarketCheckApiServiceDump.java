package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.util.UUID;

public interface MarketCheckApiServiceDump {

    void saveCitiesInFl() throws IOException;

    void getMarketCheckData() throws IOException, AppraisalException;


    void saveMarketCheckDealer(String city) throws WebClientResponseException;
  //  public void saveMarketCheckInv(Long mktDealerID, Set<String> newExtColor, Set<String> newIntColor) throws WebClientResponseException, AppraisalException, IOException;
     void getAllDealersInFlorida() throws IOException;

    void addMktInvVehicles(Long start,Long end) throws AppraisalException, IOException;

    void saveMarketCheckInv(String mktDealerID) throws WebClientResponseException, AppraisalException, IOException;

    void storeDataFromMkDealerToDealerReg(Long start,Long end) throws AppraisalException;

    void storeDataFromMkInventoryToAppr(Long start,Long end) throws AppraisalException;
}
