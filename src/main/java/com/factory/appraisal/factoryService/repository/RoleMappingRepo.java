package com.factory.appraisal.factoryService.repository;


import com.factory.appraisal.factoryService.persistence.model.ERoleMapping;
import com.factory.appraisal.factoryService.persistence.model.EUserRegistration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface RoleMappingRepo extends JpaRepository<ERoleMapping,Long> {


    @Query("select r from ERoleMapping r where r.user.id=:id and r.valid=true")
    List<ERoleMapping> findByUserRoll(UUID id);

    @Query(value = "SELECT e FROM ERoleMapping e where e.valid=true AND e.user.id=:userId")
    ERoleMapping findByUserId(UUID userId);

    @Query("select r from ERoleMapping r where r.manager.id=:userId and r.valid=true")
    List<ERoleMapping> findByManagerId(UUID userId);

    @Query("select r from ERoleMapping r where (r.role.roleGroup= 'D' OR r.role.roleGroup='P') and r.valid=true")
    List<ERoleMapping> getDealerList();
   @Query("select r.user.id from ERoleMapping r where r.manager.id=(select r.manager.id from ERoleMapping r" +
           " where r.user.id=:userId and r.valid=true )and r.valid=true")
    List<UUID> findUsersUnderManager(UUID userId);

}
