package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EUserWishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface UserWishlistRepository extends JpaRepository<EUserWishlist,Long> {

    /**
     * this method will return EUserWishList
     * @param id this is user id
     * @param b this is the boolean value
     * @param pageable this is the pagenation value
     * @return list of EUserWishList
     */

    Page<EUserWishlist> findByUserIdAndValidOrderByModifiedOnDesc(Long id, boolean b, Pageable pageable);

    /**
     * This method finding Appraisal in EuserwishList
     * @param userId receive from UI
     * @param apprId receive from UI
     * @return EUserList
     */

    @Query(value = "SELECT e FROM EUserWishlist e WHERE e.appRef.id=:apprId AND e.user.id=:userId")
    EUserWishlist findByAppraisalId(Long userId,Long apprId);





}
