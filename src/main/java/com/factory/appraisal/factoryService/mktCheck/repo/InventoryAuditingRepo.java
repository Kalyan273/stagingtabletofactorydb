package com.factory.appraisal.factoryService.mktCheck.repo;

import com.factory.appraisal.factoryService.TrackChanges;
import com.factory.appraisal.factoryService.mktCheck.model.InventoryAuditing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InventoryAuditingRepo  extends JpaRepository<InventoryAuditing,Long> {

}
