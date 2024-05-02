package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.TotalMembersView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalMembersRepo extends JpaRepository<TotalMembersView,Long> {

}
