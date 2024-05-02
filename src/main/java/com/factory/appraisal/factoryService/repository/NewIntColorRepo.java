package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.NewIntColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewIntColorRepo extends JpaRepository<NewIntColor,String > {
}
