package com.factory.appraisal.factoryService.mktCheck.repo;


import com.factory.appraisal.factoryService.mktCheck.model.EMkDealerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface MktDealerRepo extends JpaRepository<EMkDealerRegistration,Long> {

    @Query(value = "select e.id from EMkDealerRegistration e where e.mkDealerId=:dealerId")
    Long findDealerByMktId(String dealerId);
    @Query(value = "select e.mkDealerId from EMkDealerRegistration e ")
    List<Long> findAllDealerMktId();

    @Query(value = "select e.mkDealerId from EMkDealerRegistration e where e.id between :start and :stop")
    List<String> findInRangeDealerMktId(Long start ,Long stop);

    @Query(value = "select e from EMkDealerRegistration e where e.id between :start and :end ")
    List<EMkDealerRegistration> findByRange(Long start, Long end );

    @Query(value = "select e from EMkDealerRegistration e where e.mkDealerId=:dealerId")
    EMkDealerRegistration findDlrByMktId(String dealerId);

    @Query(value = "select e from EMkDealerRegistration e where e.userUuid=:id")
    EMkDealerRegistration findDealerByUUID(String id);

    @Query(value = "SELECT e FROM EMkDealerRegistration e WHERE e.userUuid IS NULL")
    List<EMkDealerRegistration> findDealerWthOutUUID();


    @Query(value = "select e.mkDealerId from EMkDealerRegistration e where e.factoryMember= true")
    List<Long> getAllDlrId();

    @Query(value = "select e.mkDealerId from EMkDealerRegistration e where e.factoryMember= false")
    List<Long> getAllNonDlrId();

    @Query(value = "SELECT e FROM EMkDealerRegistration e WHERE e.mkDealerId=:mkDealerId")
    EMkDealerRegistration findMktDlrBymktdlrId(String mkDealerId);

    @Query(value = "SELECT e FROM EMkDealerRegistration e WHERE e.city in ('Cape Coral','Cocoa','Dade City') and e.valid=true")
    List<EMkDealerRegistration> findByCity();

}
