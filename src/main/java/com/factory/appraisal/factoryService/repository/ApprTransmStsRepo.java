package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EApprVehTransSts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprTransmStsRepo extends JpaRepository<EApprVehTransSts,Long> {
}
