package com.factory.appraisal.factoryService.mktCheck.repo;

import com.factory.appraisal.factoryService.mktCheck.model.EMkScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MktSchedulerRepo extends JpaRepository<EMkScheduler,Long> {

    @Query(value = "select e from EMkScheduler e where e.event=:event")
    EMkScheduler findByEvent(String event);

//    @Query(value = "select e from EMkScheduler e where e.event:eventName")
//    EMkScheduler findByEvent(String eventName);
}
