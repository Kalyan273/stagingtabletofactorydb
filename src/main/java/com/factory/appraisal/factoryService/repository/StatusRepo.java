package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatusRepo extends JpaRepository<EStatus,Long> {

    /**
     * this method will return EStatus from EStatus Table
     * @param statusId this is the status id
     * @return EStatus
     */
    @Query(value = "select e from EStatus e where e.valid=true and e.id=:statusId")
    EStatus findStsById(Long statusId);

    /**
     * this method will return EStatus status from EStatus Table
     * @param status this is the status code
     * @return EStatus
     */

    EStatus findByStatusCode(String status);

}
