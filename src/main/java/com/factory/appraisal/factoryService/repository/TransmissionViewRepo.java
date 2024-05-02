package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.TransmissionView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionViewRepo extends JpaRepository<TransmissionView,String > {
}
