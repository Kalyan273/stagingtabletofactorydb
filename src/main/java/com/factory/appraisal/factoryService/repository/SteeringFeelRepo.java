package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.ESteeringFeelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface SteeringFeelRepo extends JpaRepository<ESteeringFeelStatus,Long> {

}
