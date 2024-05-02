package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EAppraiseVehicle;
import com.factory.appraisal.factoryService.persistence.model.EFtryTraining;
import com.factory.appraisal.factoryService.persistence.model.ERoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FtryTrainingRepo extends JpaRepository<EFtryTraining,Long> {

    @Query(value = "SELECT e FROM EFtryTraining e where e.valid=true")
    List<EFtryTraining> findAllByValid();


}
