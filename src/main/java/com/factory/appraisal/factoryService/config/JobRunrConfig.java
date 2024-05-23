package com.factory.appraisal.factoryService.config;


import com.factory.appraisal.factoryService.services.MarketCheckApiServiceDump;
import com.factory.appraisal.factoryService.services.OffersService;
import com.factory.appraisal.factoryService.services.MarketCheckApiServiceDump;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


//@Configuration
public class JobRunrConfig {
    @Autowired
    private JobScheduler jobScheduler;
    @Value("${cron.schedule.expression}")
    private String cronExpression;

    @Value("${cron.schedule.dealerRegFromMktChck}")
    private String dealerRegFromMktChck;

    @PostConstruct
    public void scheduleRecurrently() {

        jobScheduler.<OffersService>scheduleRecurrently(cronExpression, x -> x.myScheduledTask());
//        jobScheduler.<MarketCheckApiServiceDump>scheduleRecurrently(dealerRegFromMktChck, z -> z.getMarketCheckDataToSaveDealers());
        jobScheduler.<OffersService>scheduleRecurrently(cronExpression, OffersService::myScheduledTask);
        jobScheduler.<MarketCheckApiServiceDump>scheduleRecurrently(Cron.hourly(), MarketCheckApiServiceDump::storeDataFromMkInventoryToAppr);

    }

/*    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }*/

}
