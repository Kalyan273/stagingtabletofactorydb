package com.factory.appraisal.factoryService.persistence.model;
// authorName:YogeshKumarV

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

/**
 * This is an Entity class  EApprVehTireCondn
 */

@Audited
@AuditOverrides({
        @AuditOverride(forClass=TransactionEntity.class, name="createdBy", isAudited=true),
        @AuditOverride(forClass=TransactionEntity.class, name="createdOn", isAudited=true),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedBy", isAudited=true),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedOn", isAudited=true),
        @AuditOverride(forClass= IdEntity.class, name="version",isAudited=true),
        @AuditOverride(forClass=IdEntity.class, name="sourceSystem", isAudited=true),
        @AuditOverride(forClass= IdEntity.class, name="valid", isAudited=true)
})
@Entity
@Table(name = "APR_VEH_TIRE_CONDN")
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "TIRE_CONDN_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EApprVehTireCondn extends TransactionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_VEH_TIRE_CONDN_SEQ")
    @GenericGenerator(name = "APR_VEH_TIRE_CONDN_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ALL_MATCH_SIZE_AND_MAKE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes allMatchingSizeAndMake;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "MISMATCH")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes mismatched;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TREAD_DEPTH_10BY32")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes treadDepth10_32New;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TREAD_DEPTH_6BY32_OR_HIGH_GOOD")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes treadDepth6_32orhigherGood;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TREAD_DEPTH_4BY32_POOR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes treadDepth4_32Poor;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_WORN_UNEVEN")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes frontsWornUneven;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "REARS_WORN")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes rearsWorn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_SPARE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes noSpare;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "GOOD_SPARE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes goodSpare;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "RIM_DAMAGE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes rimDamage;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SIDWALLS_CHECKED")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes sdWallsChkd;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SPARE_ON_CAR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes spareOnCar;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "STOCK_OFFSET")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes stockOffset;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ALL_SAME_MAKE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes allSameMake;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NEED_REPLACEMENT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes needRplcmt;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "RUN_FLATS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes runFlats;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID",nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;

    /*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_WORN_UNEVEN_NEED_RPLMT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes frontsWornUnevenNeedReplacement;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "REARS_WORN_NEED_RPLMT ")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes rearsWornNeedReplacement;*/

    /*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "WTHR_CHK_SDWALL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes weatherCheckedSidewalls;*/
}