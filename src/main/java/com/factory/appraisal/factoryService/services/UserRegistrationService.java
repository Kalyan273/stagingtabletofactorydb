package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.*;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserRegistrationService {
    /**
     *This method will create user
     * Method taking UserRegistration as argument and returning a message
     * @param userRegistration This is the object  of UserRegistration
     * @return message
     */

    String createUser(UserRegistration userRegistration, Long dealerId) throws AppraisalException, MessagingException, TemplateException, IOException;


    /**
     * This method will show user based on userId
     * @param userId This is the primary key of EUserRegistration entity
     * @author YogeshKumarV
     * @return  UserRegistration
     */
    UserRegistration showInEditPage(UUID userId) throws AppraisalException;

    /**
     * This method updates the EUserRegistration based on userId
     * @param userRegistration This is the object of UserRegistration dto
     * @param userId This is the primary key of EUserRegistration entity
     * @author YogeshKumarV
     * @return  Response
     */
    Response updateUser(UserRegistration userRegistration, UUID userId) throws AppraisalException;

    /**
     * This method finds the user by userName and Password and returns User information
     * @param UserName This is username
     * @param password This is password
     * @return UserRegistration
     */
    UserRegistration findUser(String UserName,String password) throws AppraisalException;

    /**
     * This method is used to delete user
     * @param userId
     * @return
     */
    Response deleteUser(UUID userId) throws GlobalException;

    UsrDlrList corpDlr(UUID userId) throws AppraisalException;

     UsrDlrList dealerList(UUID userId) throws AppraisalException;

    Response assignFactoryUser(UUID factoryUser,UUID userId) throws AppraisalException;
    Response makeUserSubscribed(UUID userId, Boolean isSubscribed, Double amt);

    public UserExistResponse checkUser(String userName);

    public UserRegistration getUserinfo(UUID userId) throws AppraisalException;


}
