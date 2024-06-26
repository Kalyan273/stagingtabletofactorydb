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
import java.util.UUID;

/**
 * This is the Entity class ERoleMapping
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
@Table(name = "ROLE_MAPPING",schema = "factory_db")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "ROLE_MPNG_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class ERoleMapping extends TransactionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_MAPPING_SEQ")
    @GenericGenerator(name = "ROLE_MAPPING_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EUserRegistration user;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EUserRegistration manager;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = ECompany.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private ECompany company;

    @OneToOne(targetEntity = ERole.class, fetch = FetchType.LAZY)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @JoinColumn(name = "ROLE_ID",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private ERole role;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTORY_MANAGER",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EUserRegistration factoryManager;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FACTORY_SALESMAN",nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EUserRegistration factorySalesman;

    private UUID dealerAdmin;


}
