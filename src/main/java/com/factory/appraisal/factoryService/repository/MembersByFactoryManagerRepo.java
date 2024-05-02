package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.MembersByFactoryManager;
import com.factory.appraisal.factoryService.persistence.model.TransactionReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembersByFactoryManagerRepo extends JpaRepository<MembersByFactoryManager,Long>, JpaSpecificationExecutor<MembersByFactoryManager> {
    List<MembersByFactoryManager> findByFactoryManager(Long fmUserId);
  Page<MembersByFactoryManager> findByFactoryManager(Long fmUserId, Pageable pageable);

}
