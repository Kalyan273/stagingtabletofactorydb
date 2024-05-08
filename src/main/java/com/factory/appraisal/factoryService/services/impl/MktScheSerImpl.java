package com.factory.appraisal.factoryService.services.impl;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.mktCheck.model.EMkScheduler;
import com.factory.appraisal.factoryService.mktCheck.repo.MktSchedulerRepo;
import com.factory.appraisal.factoryService.services.MarketChkSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class MktScheSerImpl implements MarketChkSchedulerService {


    @Autowired
    private MktSchedulerRepo mktSchedulerRepo;


    @Override
    public Response addEvent(String eventName) {
        EMkScheduler emkScheduler= new EMkScheduler();
        emkScheduler.setEvent(eventName);
        emkScheduler.setValid(Boolean.FALSE);
        mktSchedulerRepo.save(emkScheduler);
        Response response= new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("event added");
        response.setStatus(true);
        return response;
    }

    @Override
    public EMkScheduler getInfoOfEvent(String eventName) {
        //return mktSchedulerRepo.findByEvent(eventName);
        return null;

    }
}
