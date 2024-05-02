package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.NewExtColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewExtColorRepo extends JpaRepository<NewExtColor,String > {
}
