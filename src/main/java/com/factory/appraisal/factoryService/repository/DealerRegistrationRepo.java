package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.Author;
import com.factory.appraisal.factoryService.dto.SellingDealer;
import com.factory.appraisal.factoryService.persistence.model.EDealerRegistration;
import com.factory.appraisal.factoryService.persistence.model.MembersByFactorySalesmen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Author("Yogesh Kumar V")
@Repository
@Transactional
public interface DealerRegistrationRepo extends JpaRepository<EDealerRegistration,Long>, JpaSpecificationExecutor<EDealerRegistration> {
    /**
     * This method returns the valid  EDealerRegistration object
     * @param dealerId This is the dealerId field of EDealerRegistration class
     * @author YogeshKumarV
     * @return EDealerRegistration
     */
    @Query(value = "select e from EDealerRegistration e where e.valid=true and e.id=:dealerId")
    EDealerRegistration findDealerById(Long dealerId);

    @Query(value = "select e.id from EDealerRegistration e where e.valid=true and e.mkDealerId=:mktDealerId")
    Long findDealer(Long mktDealerId);

    /**
     *it will check the dealers username is present
     * @param name
     * @return EDealer registration
     */


    @Query(value = "select e from EDealerRegistration e where e.valid=true and e.name=:name")
    String chkDlrUsrNamePresent(String name);

    @Query(value = "select e from EDealerRegistration e where e.valid=true and e.company.id = null")
    Page<EDealerRegistration> getDlrListOfcompNameNull(Pageable pageable);


    @Query(value = "select e from EDealerRegistration e where e.mkDealerId=:mktDealerId")
    EDealerRegistration findDealerByMktDlrID(Long mktDealerId);



}
