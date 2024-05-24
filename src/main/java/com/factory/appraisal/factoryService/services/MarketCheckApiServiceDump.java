package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MarketCheckApiServiceDump {

    void saveCitiesInFl() throws IOException;

    void saveMarketCheckDealer(String city) throws WebClientResponseException;

    void storeDataFromMkInventoryToAppr() throws AppraisalException, JRException, IOException, JDOMException;

    public void storeDataFromMkDealerToDealerReg() throws AppraisalException, MessagingException, TemplateException, IOException, MessagingException ;

    Response mkFacDlrInvDumpfrMem() throws AppraisalException, IOException;

    Response mkFacDlrInvDumpfrNonMem() throws AppraisalException, IOException;

    Response getMarketCheckDataToSaveDealers() throws IOException, AppraisalException;




    Response syncMkDlrToFactorySch() throws AppraisalException, MessagingException, TemplateException, IOException;
}
