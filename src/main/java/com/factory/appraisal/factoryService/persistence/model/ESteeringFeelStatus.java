package com.factory.appraisal.factoryService.persistence.model;
//@author:Rupesh Khade

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
 * This is an Entity class  ESteeringFeelStatus
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
@Table(name = "STEERING_FEEL_STATUS")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "STEERING_STS_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class ESteeringFeelStatus extends TransactionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STEERING_FEEL_STATUS_SEQ")
    @GenericGenerator(name = "STEERING_FEEL_STATUS_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID", nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NORMAL_OPERATION")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes normalOperation;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "POWER_SEARING_PUMP_WHINE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes powerSearingPumpWhine;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SLOPPY_STEERING_WHEEL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes sloppySteeringWheel;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_POWER_ASSIST")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes noPowerAssist;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PULLS_TO_THE_LEFT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes pullsToLeft;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PULLS_TO_THE_RIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes pullsToRight;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BAD_TIE_RODS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes badTieRods;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "STEERING_WHEEL_WOBBLE_AT_SPEED")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes steeringWheelWobble;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BAD_STEERING_RACK")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes badSteeringRack;

}
