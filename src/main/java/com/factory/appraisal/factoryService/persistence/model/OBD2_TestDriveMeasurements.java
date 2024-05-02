package com.factory.appraisal.factoryService.persistence.model;

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Table(name = "TEST_DRIVE_MEASURE")
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
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "TD_MEASURE_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class OBD2_TestDriveMeasurements extends TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEST_DRIVE_MEASURE_SEQ")
    @GenericGenerator(name = "TEST_DRIVE_MEASURE_SEQ", strategy= AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;
    private String dataInterval;
    private String engineTempTest;
    private String engineRpm;
    private String driveGear;
    private String vehicleSpeed;
    private String voltage;
    private String upstbk1;
    private String upstbk2;
    private String dwnstbk1;
    private String dwnstbk2;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EAppraiseVehicle.class,fetch = FetchType.LAZY)
    @JoinColumn(name="APPR_REF_ID",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EAppraiseVehicle apprRef;
}
