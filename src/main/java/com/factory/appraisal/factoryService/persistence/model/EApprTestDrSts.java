package com.factory.appraisal.factoryService.persistence.model;

/**
 * This is an Entity class EApprTestDrSts
 * @author Rupesh Khade
 */


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
@Table(name = "APR_TEST_DR_STATUS")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "TD_STATUS_ID"))
@AttributeOverride(name = "valid",column= @Column(name = "IS_ACTIVE"))
public class EApprTestDrSts extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APR_TEST_DR_STATUS_SEQ")
    @GenericGenerator(name = "APR_TEST_DR_STATUS_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EAppraiseVehicle.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPR_REF_ID ", nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EAppraiseVehicle appraisalRef;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprVehAcCondn apprVehAcCondn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprVehInteriCondn apprVehInteriCondn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprVehOilCondn apprVehOilCondn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprVehStereoSts apprVehStereoSts;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprVehTireCondn apprVehTireCondn;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EVehDWarnLightStatus vehDrWarnLightSts;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private ESteeringFeelStatus steeringFeel;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EBookAndKeys bookAndKeys;


    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprBrakingSysSts apprBrakingSysSts;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprEnginePer apprEnginePer;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprVehTransSts apprTransmissionSts;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus", cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private ERearWndwDmg rearWindow;

    @Column(name = "ENGINE_TYPE")
    private String engineType;
    @Column(name = "TRANSMISSION_TYPE ")
    private String transmissionType;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DOOR_LOCKS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes doorLock ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_L_WIN_STATUS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes  fLWinStatus ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_R_WIN_STATUS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes  fRWinStatus ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "R_L_WIN_STATUS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes  rLWinStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "R_R_WIN_STATUS")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes   rRWinStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "INTR_COLOR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes  intrColor ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "EXTR_COLOR")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes  extrColor ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOF_TYPE")
    @Where(clause = "IS_ACTIVE = true")
    private EConfigCodes   roofTypes ;



    @Column(name = "APR_FOLLOW_UP ")
    private String apprFollowUp;

    @Column(name = "APR_INV_STATUS ")
    private String apprInvenSts;

    @Column(name = "EXTR_DMG_STATUS ")
    private Boolean externalDmgSts;

    @Column(name = "F_DR_SIDE_DMG_STS")
    private String frDrSideDmgSts;

    @Column(name = "F_DR_SIDE_DMG_DESC")
    private String frDrSideDmgTxtBox;

    @Column(name = "F_P_SIDE_DMG_STS ")
    private String frPassenSideDmgSts;

    @Column(name = "F_P_SIDE_DMG_DESC")
    private String frPassenSideDmgTxtBox;

    @Column(name = "PNTWRK_FD_SIDE_STS ")
    private String frDrSidePntWrkSts;

    @Column(name = "PNTWRK_FD_SIDE_STS_DESC")
    private String frDrSidePntWrkTxtBox;

    @Column(name = "PNTWRK_FP_SIDE_STS")
    private String frPassenSidePntWrk ;

    @Column(name = "PNTWRK_FP_SIDE_STS_DESC")
    private String frPassenSidePntWrkTxtBox;

    @Column(name = "PNTWRK_RD_SIDE_STS")
    private String rearDrSidePntWrk;

    @Column(name = "PNTWRK_RD_SIDE_STS_DESC")
    private String rearDrSidePntWrkTxtBox;

    @Column(name = "PNTWRK_RP_SIDE_STS")
    private String rearPassenSidePntWrk;

    @Column(name = "PNTWRK_RP_SIDE_STS_DESC")
    private String rearPassenSidePntWrkTxtBox;

    @Column(name = "PNTWRK_STATUS")
    private Boolean paintWork;

    @Column(name = "R_DR_SIDE_DMG_STS ")
    private String rearDrSideDmgSts;

    @Column(name = "R_DR_SIDE_DMG_DESC")
    private String rearDrSideDmgTxtBox;

    @Column(name = "R_PASS_SIDE_DMG_STS ")
    private String rearPassenSideDmgSts;

    @Column(name = "R_PASS_SIDE_DMG_DESC")
    private String rearPassenSideDmgTxtBox;

    @Column(name = "WS_BUY_FIG_STS ")
    private Boolean pushForBuyFig=false;

    @Column(name = "KEY_ASSURE_YES")
    private String keyAssureYes;

    @Column(name = "SUBS_KEY_ASSURE")
    private Boolean subscribToKeyAssure;

    @Column(name = "KEY_ASSURE_FILE")
    private String keyAssureFiles;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "WIND_SHIELD_DMG")
    @Where(clause = "IS_ACTIVE = true")
    private  EConfigCodes  windShieldDmg ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EConfigCodes.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_STS")
    @Where(clause = "IS_ACTIVE = true")
    private  EConfigCodes titleSt ;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "tdStatus",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EAprVehImg aprVehImg;


}