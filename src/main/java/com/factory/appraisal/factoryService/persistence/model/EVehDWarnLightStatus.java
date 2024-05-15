package com.factory.appraisal.factoryService.persistence.model;
//@Author:Yudhister vijay

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
 * This is an Entity class EVehDWarnLightStatus
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
@Table(name = "DASH_WARN_LIGHT_STS")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "WL_STATUS_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))

public class EVehDWarnLightStatus extends TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DASH_WARN_LIGHT_STS_SEQ")
    @GenericGenerator(name = "DASH_WARN_LIGHT_STS_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID",nullable = false)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_FAULTS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes noFaults;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ABS_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes absFault;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "AIRBAG_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes airBagFault;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BATTERY_LIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes batteryLight;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAKE_SYSTEM_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes brakeSystemFault;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAKE_PAD_WEAR_LIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes brakePadWearLight;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARGING_SYS_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes chargingSystemFault;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COOLING_SYS_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes clngSysFault;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COOLANT_LOW")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes coolantLow;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHECK_ENG_LIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes checkEngineLight;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "OIL_PRESSURE_LOW")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes oilPressureLow;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_SOON")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes serviceSoon;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHNG_OIL_INDICATOR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes chngOilIndctr;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "LOW_OIL_INDCTR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes lowOilIndctr;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TRACTION_CONTROL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes tractionControl;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSMISSION_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes transmiFault;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TPMS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes tpms;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DPF_LIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes dislParticulateFilt;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_BTRY_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes mainBtryFault;

    /*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BATTERY_FAULT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes batteryFault;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAKE_SYSTEM")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes brakeSystem;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAKE_PAD_WEAR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes brakePadWear;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARGING_SYS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes chargingSystem;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COOLANT_LEVEL")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes coolantLevel;*/


/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COOLANT_TEMP")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes coolantTemp;*/

    /*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "OIL_PRESSURE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes oilPressure;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_ENG_SOON")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes serviceEngineSoon;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "STEERING_FAULTS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes steeringFaults;*/

/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SUSPENSION_SYSTEM")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes suspensionSystem;*/



/*    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEF_LIGHT")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes dislExhFluidLight;*/



}