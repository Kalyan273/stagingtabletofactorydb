package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.UUID;

public interface MarketCheckApiServiceDump {

    void saveCitiesInFl() throws IOException;

    void getMarketCheckData() throws IOException, AppraisalException;

    void storeDataFromMkDealerToDealerReg() throws AppraisalException, MessagingException, TemplateException, IOException;


    void saveMarketCheckDealer(String city) throws WebClientResponseException;
  //  public void saveMarketCheckInv(Long mktDealerID, Set<String> newExtColor, Set<String> newIntColor) throws WebClientResponseException, AppraisalException, IOException;
     void getAllDealersInFlorida() throws IOException;

    void addMktInvVehicles(Long start,Long end) throws AppraisalException, IOException;

    void saveMarketCheckInv(String mktDealerID) throws WebClientResponseException, AppraisalException, IOException;



    void storeDataFromMkInventoryToAppr() throws AppraisalException, JRException, IOException, JDOMException;


    Response mkFacDlrInvDumpfrMem() throws AppraisalException, IOException;

    Response mkFacDlrInvDumpfrNonMem() throws AppraisalException, IOException;

    Response getMarketCheckDataToSaveDealers() throws IOException, AppraisalException;

}
