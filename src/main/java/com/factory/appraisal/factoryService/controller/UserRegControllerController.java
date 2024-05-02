package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.*;
import com.factory.appraisal.factoryService.services.UserRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
@Api(tags = "User Registration", value = "User Module")
public class UserRegControllerController {

    Logger log = LoggerFactory.getLogger(UserRegControllerController.class);

    @Autowired
    private UserRegistrationService userRegistrationService;




    /**
     * This method saves the new user by calling createUser() method of UserRegistrationService
     * @param userRegistration This is the object coming from ui
     * @param dealerId This is the dealer id coming from ui in header
     * @return
     */
/*    @ApiOperation(value = "Add users in database")
    @PostMapping("/registerUser")
    public ResponseEntity<Response> userCreation(@RequestBody @Validated UserRegistration userRegistration, @RequestHeader("dealerId")Long dealerId) throws AppraisalException {
        log.info("User Creation method is triggered");
        Response response=userRegistrationService.createUser(userRegistration,dealerId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }*/
    /**
     * This method updates the EUserRegistration entity by calling updateUser() method of UserRegistrationService
     * @param userRegistration This is the object of UserRegistration dto
     * @param userId This is primary key of EUserRegistration entity
     * @author YogeshKumarV
     * @return  Response
     */
    @ApiOperation(value = "Edit users")
    @PostMapping("/userUpdate")
    public ResponseEntity<Response> modifyUser(@RequestBody @Validated UserRegistration userRegistration, @RequestHeader("userId") UUID userId) throws AppraisalException {
        log.info("User Upadte method is Triggered **Controller**");
        Response response=userRegistrationService.updateUser(userRegistration,userId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
    /**
     * This method will show dealers based on userId by calling showInEditPage() method of UserRegistrationService
     * @param userId This is primary key of EUserRegistration entity
     * @author YogeshKumarV
     * @return  UserRegistration
     */
    @ApiOperation(value = "show users")
    @PostMapping("/showUser")
    public ResponseEntity<UserRegistration> showUserInEditPage(@RequestHeader("userId") UUID userId) throws AppraisalException {
        log.info("Showing user in edit page is triggered **Controller**");
        UserRegistration userRegistration = userRegistrationService.showInEditPage(userId);
        log.debug("OBJECT FOR SHOWING USER IN EDIT PAGE",userRegistration);
        return new ResponseEntity<>(userRegistration,HttpStatus.OK);
    }

    /**
     * This method returns user information
     * @param userName this is username
     * @param password this is password
     * @return UserRegistration
     */
    @ApiOperation(value = "find users by userName and Password")
    @PostMapping("/findUser")
    public ResponseEntity<UserRegistration> findUser(@RequestHeader("userName") String userName,@RequestHeader("password") String password) throws AppraisalException {
        log.info("Find user by userName and Password is triggered **Controller**");
        UserRegistration userRegistration = userRegistrationService.findUser(userName,password);
        return new ResponseEntity<>(userRegistration,HttpStatus.OK);
    }

    /**
     * This method is used to delete user from the database
     * @param userId
     * @return
     * @throws GlobalException
     */
    @ApiOperation(value="This method is used to delete user")
    @PostMapping("/deleteuser")
    public ResponseEntity<Response> removeUser(@RequestHeader("userId") UUID userId) throws GlobalException {
        return new ResponseEntity<>(userRegistrationService.deleteUser(userId),HttpStatus.OK);
    }

    /**
     * This method will return corporate dealer List
     * @param userId
     * @return
     * @throws AppraisalException
     */
    @PostMapping("/corDlrList")
    public ResponseEntity<Response> crpDlrList(@RequestHeader("userId") UUID userId) throws AppraisalException {
        log.info("Getting corporate DealerList **Controller**");
        return new ResponseEntity<>(userRegistrationService.corpDlr(userId),HttpStatus.OK);
    }

    /**
     * This method will return dealer list  under Corporate Admin
     * @param userId
     * @return
     * @throws AppraisalException
     */
    @PostMapping("/dlrList")
    public ResponseEntity<Response> dealerList(@RequestHeader("userId") UUID userId) throws AppraisalException {
        log.info("Getting DealerList under Corporate Admin **Controller**");
        return new ResponseEntity<>(userRegistrationService.dealerList(userId),HttpStatus.OK);
    }


    /**
     * This method will assign factory salesman or factory manager to user
     * @param factoryUser
     * @param userId
     * @return
     * @throws AppraisalException
     */
    @PostMapping("/assignFactoryUser")
    public ResponseEntity<Response> assignFactoryUser(@RequestParam ("factoryUserId") UUID factoryUser,@RequestParam("userId") UUID userId) throws AppraisalException {
        Response response = userRegistrationService.assignFactoryUser(factoryUser, userId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    /**
     *
     * @param userId
     * @param isSubscribed
     * @param amt
     * @return
     */
    @PostMapping("/subscribeUser")
    public ResponseEntity<Response> makeUserSubscribed(@RequestParam("userId") UUID userId, @RequestParam("isSubscribed") Boolean isSubscribed, @RequestParam("amt") Double amt){
        Response response = userRegistrationService.makeUserSubscribed(userId, isSubscribed, amt);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }



/*    @PostMapping("/sendApi")
    public String api(@RequestBody @Validated UserRegistration userRegistration, @RequestHeader("dealerId")Long dealerId) throws AppraisalException {
        String role = userRegistrationService.createUser(userRegistration,dealerId);
        return role;
    }*/
    @PostMapping("/checkUserName")
    public ResponseEntity<UserExistResponse> searchUserName(@RequestHeader("userName")String userName) throws AppraisalException {
        UserExistResponse userExistResponse = userRegistrationService.checkUser(userName);
        return new ResponseEntity<>(userExistResponse,HttpStatus.ACCEPTED);
    }



    @ApiOperation(value = "find users by userId")
    @PostMapping("/getUser")
    public ResponseEntity<UserRegistration> getUser(@RequestHeader("userId") UUID userId) throws AppraisalException {
        log.info("Find user by userName and Password is triggered **Controller**");
        UserRegistration userRegistration = userRegistrationService.getUserinfo(userId);
        return new ResponseEntity<>(userRegistration,HttpStatus.OK);
    }


}
