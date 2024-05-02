package com.factory.appraisal.factoryService.persistence.model;
//@Author:Yudhister vijay

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

/**
 * This is an Entity class  ESignDet
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
@Table(name = "E_SIGN_DET")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "E_SIGN_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class ESignDet extends TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "E_SIGN_DET_SEQ")
    @GenericGenerator(name = "E_SIGN_DET_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Column(name = "E_SIGN_DOC")
    private String signDocument;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EAppraiseVehicle.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPR_REF_ID", nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EAppraiseVehicle apprRef;

    @Column(name = "ADJ_WS_POOR")
    private String adjustedWholePoor;
    @Column(name = "ADJ_WS_FAIR")
    private String adjustedWholeFair;
    @Column(name = "ADJ_WS_GOOD")
    private String adjustedWholeGood;
    @Column(name = "ADJ_WS_VG")
    private String adjustedWholeVeryGood;
    @Column(name = "ADJ_WS_XLNT")
    private String adjustedWholeExcelnt;
    @Column(name = "ADJ_FINANACE_POOR")
    private String adjustedFinanPoor;
    @Column(name = "ADJ_FINANACE_FAIR")
    private String adjustedFinanFair;
    @Column(name = "ADJ_FINANACE_GOOD")
    private String adjustedFinanGood;
    @Column(name = "ADJ_FINANACE_VG")
    private String adjustedFinanVeryGood;
    @Column(name = "ADJ_FINANACE_XLNT")
    private String adjustedFinanExcelnt;
    @Column(name = "ADJ_RETAIL_POOR")
    private String adjustedRetailPoor;
    @Column(name = "ADJ_RETAIL_FAIR")
    private String adjustedRetailFair;
    @Column(name = "ADJ_RETAIL_GOOD")
    private String adjustedRetailGood;
    @Column(name = "ADJ_RETAIL_VG")
    private String adjustedRetailVeryGood;
    @Column(name = "ADJ_RETAIL_XLNT")
    private String adjustedRetailExcelnt;


}