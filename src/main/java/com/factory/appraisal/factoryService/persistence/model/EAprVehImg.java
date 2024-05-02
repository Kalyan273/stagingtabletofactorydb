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
@Table(name = "APR_VEH_IMG")
@EqualsAndHashCode(callSuper = true)
@Data
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "VEH_IMG_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EAprVehImg extends TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_VEH_IMG_SEQ")
    @GenericGenerator(name = "APR_VEH_IMG_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Column(name = "F_DRV_S_DMG_IMG")
    private String frDrSideDmgPic ;

    @Column(name = "F_PAX_S_DMG_IMG")
    private String frPassenSideDmgPic ;

    @Column(name = "R_DRV_S_DMG_IMG")
    private String rearDrSideDmgPic ;

    @Column(name = "R_PAX_S_DMG_IMG")
    private String rearPassenSideDmgPic ;

    @Column(name = "F_DRV_S_PNTWRK_IMG")
    private String frDrSidePntWrkPic ;

    @Column(name = "F_PAX_S_PNTWRK_IMG")
    private String frPassenSidePntWrkPic ;

    @Column(name = "R_DRV_S_PNTWRK_IMG")
    private String rearDrSidePntWrkPic ;

    @Column(name = "R_PAX_S_PNTWRK_IMG")
    private String rearPassenSidePntWrkPic ;

    @Column(name = "VEH_IMG1")
    private String vehiclePic1 ;

    @Column(name = "VEH_IMG2")
    private String vehiclePic2 ;

    @Column(name = "VEH_IMG3")
    private String vehiclePic3 ;

    @Column(name = "VEH_IMG4")
    private String vehiclePic4 ;

    @Column(name = "VEH_IMG5")
    private String vehiclePic5 ;

    @Column(name = "VEH_IMG6")
    private String vehiclePic6 ;

    @Column(name = "VEH_IMG7")
    private String vehiclePic7 ;

    @Column(name = "VEH_IMG8")
    private String vehiclePic8 ;

    @Column(name = "VEH_IMG9")
    private String vehiclePic9 ;

    @Column(name = "ENG_VIDEO")
    private String vehicleVideo1 ;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EApprTestDrSts.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TD_STATUS_ID", nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;



}
