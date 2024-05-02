package com.factory.appraisal.factoryService.controller;

import com.factory.appraisal.factoryService.ExceptionHandle.AppraisalException;
import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.services.MarketCheckApiServiceDump;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/appraisal")
@Api(tags = "MarketCheckDataController", value = "MarketCheck Info.")
public class GetMarketCheckDataController {

    @Autowired
    private MarketCheckApiServiceDump service;

    @ApiOperation(value = "This method is used to get dealer from marketCheck")
    @PostMapping("/getdealers")
    public ResponseEntity<Response> addDealer() throws IOException {

        service.getAllDealersInFlorida();
        Response response=new Response();
        response.setMessage("data added successfully");
        response.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @ApiOperation(value="save marketCheck inventory ",response = Response.class)
    @PostMapping("/saveMktInv")
    public ResponseEntity<Response> saveMktInv(@RequestParam Long start,@RequestParam Long end) throws AppraisalException, IOException {
        service.addMktInvVehicles(start, end);
        Response response=new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Inventory vehicles added successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @ApiOperation(value = "This method is used to get dealer from marketCheck schema")
    @PostMapping("/getDealersFromMarketCheck")
    public ResponseEntity<Response> addDealerInFactoryDB(@RequestParam Long startDealerID,@RequestParam Long endDealerId) throws AppraisalException {

        service.storeDataFromMkDealerToDealerReg(startDealerID, endDealerId);
        Response response=new Response();
        response.setMessage("data added successfully");
        response.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "This method is used to get Appraisals from marketCheck schema")
    @PostMapping("/getApprFromMarketCheck")
//    public ResponseEntity<Response> addInventoryVehiInFactoryDB(@RequestParam UUID startUserID, @RequestParam UUID endUserId) throws AppraisalException;
        public ResponseEntity<Response> addInventoryVehiInFactoryDB(@RequestParam Long start,@RequestParam Long end) throws AppraisalException {
        service.storeDataFromMkInventoryToAppr(start,end);
        Response response=new Response();
        response.setMessage("data added successfully");
        response.setCode(HttpStatus.OK.value());
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }


}
