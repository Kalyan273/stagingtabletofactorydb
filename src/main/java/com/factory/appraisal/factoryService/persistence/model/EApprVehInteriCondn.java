package com.factory.appraisal.factoryService.persistence.model;
//author:Kalyan Dey

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
 * This is an Entity class  EApprVehInteriCondn
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
@Table(name = "APR_VEH_INTR_CONDN")
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "INTR_CONDN_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EApprVehInteriCondn extends TransactionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_VEH_INTR_CONDN_SEQ")
    @GenericGenerator(name = "APR_VEH_INTR_CONDN_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID",  nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLEAN_FL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes CleanFL;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "GOOD_MNR_RPR_ND")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes goodMnrRepaisNeed;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SMOKERS_CAR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes smokersCar;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ODD_SMELL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes oddSmelling;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "VERY_DIRTY")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes veryDirty;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "STG_PET_SMELL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes strongPetSmell;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DRV_SEAT_WEAR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes driversSeatWear;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "HL_NEED_RPLC")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes headlineNeedRplc;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DRV_SEAT_RIPPED")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes driverSeatRipped;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DASH_CRK_MNR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes dashCrackedMinor;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DASH_CRK_BRKN_MAJ")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes dashCrackBrknMaj;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PAX_SEAT_RIPPED")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes passenSeatRipped;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CARPET_BADLY_WORN")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes carpetBadlyWorn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "INTR_TRIM_BRKN_MS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes interTrimBrknnMiss;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "POOR_NEEDS_RPR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes poorNeedsRepair;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "AC_SEAT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes acSeat;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DR_PWR_ST")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes driversPowerSeat;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "HDLNR_STAIND")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes headLinerStained;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "HEAT_ST")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes heatedSeats;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PSNGR_PWR_ST")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes psngrPowerSeat;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "RR_ST_RIP")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes rearSeatRipped;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SPRT_ST")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes sportSeats;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SNRF_MNFR_LEAK")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes sunOrMoonRfLeaking;
}
