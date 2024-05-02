package com.factory.appraisal.factoryService.controller;


import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.GlobalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.dto.*;
import com.factory.appraisal.factoryService.persistence.model.EDealerRegistration;
import com.factory.appraisal.factoryService.services.DealerRegistrationService;
import com.factory.appraisal.factoryService.services.FilterSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/dealer")
@Api(tags = "Dealer registration", value = "Dealer Module")
public class DealerRegistrationController {
    Logger log = LoggerFactory.getLogger(DealerRegistrationController.class);

    @Autowired
    private DealerRegistrationService dealerRegistrationService;
    @Autowired
    private FilterSpecificationService specService;

    /**
     * This method saves the new dealer by calling createDealer() method of DealerRegistrationService
     * @param dealerRegistration This is the object coming from ui
     * @return
     */
    @ApiOperation(value = "Add dealer in database")
    @PostMapping("/savedealer")
    public ResponseEntity<Response> dealerCreation(@RequestBody @Validated DealerRegistration dealerRegistration) throws AppraisalException {

        log.info("Dealer Creation method is triggered");
        String message=dealerRegistrationService.createDealer(dealerRegistration);
        Response response= new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(message);
        response.setStatus(true);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
    /**
     * This method updates the EDealerRegistration entity by calling updateDealer() method of DealerRegistrationService
     * @param dealerRegistration This is the object of DealerRegistration dto
     * @param dealerId This is the object of EUserRegistration entity
     * @author YogeshKumarV
     * @return  Response
     */
    @PostMapping("/dealerUpdate")
    public ResponseEntity<Response> modifyDealer(@RequestBody @Validated DealerRegistration dealerRegistration, @RequestHeader("dealerId") Long dealerId, @RequestHeader ("userId") UUID userId) throws AppraisalException, IOException {

        log.info("Dealer update method is triggered **Controller**");
        Response response = dealerRegistrationService.updateDealer(dealerRegistration, dealerId, userId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * This method will show dealers based on dealerId by calling showInEditPage() method of DealerRegistrationService
     * @param dealerId This is primary key of EUserRegistration entity
     * @author YogeshKumarV
     * @return  DealerRegistration
     */
    @PostMapping("/showDealer")
    public ResponseEntity<DealerRegistration> showDealerInEditPage(@RequestHeader("dealerId") Long dealerId) throws AppraisalException {
        log.info("Showing Dealer in Edit page is triggered **Controller**");
        DealerRegistration dealerRegistration = dealerRegistrationService.showInDealerEditPage(dealerId);
        log.debug("OBJECT FOR SHOWING DEALER IN EDIT PAGE **Controller");
        return new ResponseEntity<>(dealerRegistration,HttpStatus.OK);
    }

    /**
     * This method is used to delete dealer
     * @param dealerId
     * @return
     * @throws GlobalException
     */
    @ApiOperation(value = "This method is used to delete dealer")
    @PostMapping("/deletedealer")
    public ResponseEntity<Response> removeDealer(@RequestHeader("dealerId") Long dealerId) throws GlobalException {
        return new ResponseEntity<>(dealerRegistrationService.deleteDealer(dealerId),HttpStatus.OK);
    }


    @ApiOperation(value = "show Dealer List")
    @PostMapping("/showDealerList")
    public ResponseEntity<DlrList> showDlrDetails(@RequestParam Integer pageNo, @RequestParam Integer pageSize) throws AppraisalException, GlobalException {
        DlrList dlrList = dealerRegistrationService.getDlrList(pageNo,pageSize);
        return new ResponseEntity<>(dlrList,HttpStatus.OK);
    }

    @PostMapping("/searchDlrList")
    public ResponseEntity<DlrList> searchAlDlrList(@RequestBody DealerRegistration filter) throws AppraisalException {
        DlrList dlrList = specService.srchByDlrList(filter);
        return new ResponseEntity<>(dlrList,HttpStatus.OK);
    }


    @PostMapping("setCompIdInDealer")
    public ResponseEntity<Response> setCompIdInDealer(@RequestHeader("dealerId") Long dealerId,@RequestHeader("compId") Long compId){
        Response response = dealerRegistrationService.setCompIdToDealer(dealerId, compId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
