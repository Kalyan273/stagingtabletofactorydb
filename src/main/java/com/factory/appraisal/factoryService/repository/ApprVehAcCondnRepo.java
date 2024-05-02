package com.factory.appraisal.factoryService.repository;
//Author:Rupesh khade
import com.factory.appraisal.factoryService.persistence.model.EApprVehAcCondn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ApprVehAcCondnRepo extends JpaRepository<EApprVehAcCondn,Long> {
}
