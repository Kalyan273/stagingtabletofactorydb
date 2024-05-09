package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.UserRegistration;
import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JRException;
import org.jdom2.JDOMException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.UUID;


public interface EmailService {

    /**
     * This method sends Email to user who creates appraisal
     * @param userId receiving user id from UI in header
     * @return Response
     */
    Response sendCreationEmail(UUID userId) throws AppraisalException, TemplateException, IOException, MessagingException;

    /**
     * This method sends updates in Email to buyer
     * @param offerId receiving user id from UI in header
     * @return Response
     */

    Response offerUpdateEmail(Long offerId) throws AppraisalException, MessagingException, TemplateException, IOException;


    public void sendMailWithAttachment(Long offerId) throws MessagingException, IOException, AppraisalException, JRException, JDOMException, GlobalException;
    void sendToUser(UserRegistration userRegistration) throws MessagingException, IOException, TemplateException;

}
