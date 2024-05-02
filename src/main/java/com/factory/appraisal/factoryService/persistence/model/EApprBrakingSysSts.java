package com.factory.appraisal.factoryService.persistence.model;


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
 * This is an Entity class  EApprBrakingSysSts
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
@Table(name = "APR_VEH_BRAKING_SYS")
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "BRK_SYS_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))

public class EApprBrakingSysSts extends TransactionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_VEH_BRAKING_SYS_SEQ")
    @GenericGenerator(name = "APR_VEH_BRAKING_SYS_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID", nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ISSUES_GOOD_FEEL_STOPS_WELL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes noIssuesGoodFeelStopsWell;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SOFT_PEDAL_HARD_STOPPING")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes softPedalHardStopping;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "VBRT_IN_PEDAL_AND_STEERING_WHEEL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes vibrationsInPedalAndSteeringWheel;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PULLS_RIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes pullsRight;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PULLS_LEFT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes pullsLeft;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "GRINDING_NOISE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes grindingNoise;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "HRD_PEDAL_HRD_TO_STOP")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes hardPedalHardToStop;
}
