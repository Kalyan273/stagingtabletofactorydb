package com.factory.appraisal.factoryService.persistence.model;


//kalyan

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
 * This is an Entity class  EApprVehStereoSts
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
@Table(name = "APR_VEH_STEREO_STATUS")
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "STEREO_STS_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EApprVehStereoSts extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_VEH_STEREO_STATUS_SEQ")
    @GenericGenerator(name = "APR_VEH_STEREO_STATUS_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID",  nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTORY_EQU_OP")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes factoryEquptOperat;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "EQPT_NOT_OP")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes factoryEquptNotOperat;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "KNOBS_MISSING")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes knobsMissing;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "AFT_MKT_NAV_NICE_SYS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes aftMktNavigaNiceSys;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "AFT_MKT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes afterMarket;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "AFT_MKT_R_ENTMT_SYS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes aftMktRearEntertainSys;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTORY_R_ENTMT_SYS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes factoryRearEntertainSys;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRO_INSTALL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes profInstall;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BROKEN_SCREEN")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes brokenScreen;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FADED_DIS_OR_BTNS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes fadedDisBtn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NOT_OPRNL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes notOperational;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATIONAL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes operational;

}