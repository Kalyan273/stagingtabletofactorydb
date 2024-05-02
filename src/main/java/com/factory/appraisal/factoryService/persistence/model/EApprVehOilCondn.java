package com.factory.appraisal.factoryService.persistence.model;


//kalyan

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.envers.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This is an Entity class  EAppraiseVehicle
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
@Table(name = "APR_VEH_OIL_CONDN")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "OIL_CONDN_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EApprVehOilCondn extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_VEH_OIL_CONDN_SEQ")
    @GenericGenerator(name = "APR_VEH_OIL_CONDN_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID",  nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLEAN_OIL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes cleanOil;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRTY_OIL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes dirtyOil;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "WTR_IN_OIL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes waterInOil;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CRT_LEVEL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes correctLevel;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "S_QUART_LOW")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes oneQuartLow;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "GT_QUART_LOW")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes greaterThanAQuartLow;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "EC_GAUGE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes electronicGauge;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ELECTRIC_VEH")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes electricVeh;

}
