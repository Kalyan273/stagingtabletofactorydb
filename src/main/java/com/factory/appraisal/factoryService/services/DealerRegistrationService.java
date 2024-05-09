package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.DealerRegistration;
import com.factory.appraisal.factoryService.dto.DlrList;
import com.factory.appraisal.factoryService.persistence.model.EDealerRegistration;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface DealerRegistrationService {

    /**
     *This Method will create dealer taking DealerRegistration Object as argument
     * and returning a message
     * @param dealerRegistration This is the object of DealerRegistration
     * @return message
     */

    String createDealer(DealerRegistration dealerRegistration) throws AppraisalException, MessagingException, TemplateException, IOException;
    /**
     * This method will show dealer based on dealerId
     * @param dealerId This is the primary key of EDealerRegistration entity
     * @author YogeshKumarV
     * @return  DealerRegistration
     */
    DealerRegistration showInDealerEditPage(Long dealerId) throws AppraisalException;
    /**
     * This method updates the EDealerRegistration based on userId
     * @param dealerRegistration This is the object of DealerRegistration dto
     * @param dealerId This is the primary key of EDealerRegistration entity
     * @author YogeshKumarV
     * @return  Response
     */
    Response updateDealer(DealerRegistration dealerRegistration, Long dealerId, UUID userid) throws AppraisalException, IOException;

    /**
     * This method is used to delete dealer from the database
     * @param dealerId
     * @return
     */
    Response deleteDealer(Long dealerId) throws GlobalException;

    DlrList getDlrList(Integer pageNo, Integer pageSize) throws GlobalException,AppraisalException;

    /**
     * This method set the company id to dealer
     * @param dealerId
     * @param compId
     * @return
     */
    Response setCompIdToDealer(Long dealerId,Long compId);

}
