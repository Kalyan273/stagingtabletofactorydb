package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.MakeView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakeViewRepo extends JpaRepository<MakeView,String > {
}
