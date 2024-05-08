package com.factory.appraisal.factoryService.services;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import com.factory.appraisal.factoryService.mktCheck.model.EMkScheduler;

public interface MarketChkSchedulerService {

    Response addEvent(String eventName);

    EMkScheduler getInfoOfEvent(String eventName);

}
