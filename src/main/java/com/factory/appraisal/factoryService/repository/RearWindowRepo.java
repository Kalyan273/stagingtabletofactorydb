package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.ERearWndwDmg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RearWindowRepo extends JpaRepository<ERearWndwDmg,Long> {
}
