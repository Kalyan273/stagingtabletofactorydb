package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.MembersByFactorySalesmen;
import com.factory.appraisal.factoryService.persistence.model.TransactionReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MembersByFactorySalesmenRepo extends JpaRepository<MembersByFactorySalesmen,Long>, JpaSpecificationExecutor<MembersByFactorySalesmen> {
    List<MembersByFactorySalesmen> findByFactorySalesman(Long fsUserId );
    Page<MembersByFactorySalesmen> findByFactorySalesman(Long fsUserId, Pageable pageable);

}
