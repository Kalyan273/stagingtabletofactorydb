package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EBookAndKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BookAndKeyRepo extends JpaRepository<EBookAndKeys,Long> {

}
