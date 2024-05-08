package com.factory.appraisal.factoryService.controller;


import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.services.MarketChkSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {


    @Autowired
    MarketChkSchedulerService marketChkSchedulerService;

    @PostMapping("/addEvent")
    public ResponseEntity<Response> addEvent(@RequestParam("eventName") String eventName){
        Response response = marketChkSchedulerService.addEvent(eventName);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
