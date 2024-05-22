package com.factory.appraisal.factoryService.mktCheck.model;


import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.persistence.model.IdEntity;
import com.factory.appraisal.factoryService.persistence.model.TransactionEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;

import javax.persistence.*;

@Entity
@Table(name = "mkt_inventory_aud",schema = "marketcheck")
@AuditOverrides({
        @AuditOverride(forClass= TransactionEntity.class, name="createdBy"),
        @AuditOverride(forClass=TransactionEntity.class, name="createdOn"),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedBy"),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedOn"),
        @AuditOverride(forClass= IdEntity.class, name="version"),
        @AuditOverride(forClass=IdEntity.class, name="sourceSystem"),
        @AuditOverride(forClass= IdEntity.class, name="valid")
})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "mkt_inv_aud_id"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class InventoryAuditing  extends TransactionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mkt_inv_aud_id_seq")
    @GenericGenerator(name = "mkt_inv_aud_id_seq", strategy= AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;
    private Long activeInvCount;
    private Long soldInvCount;
    private Long expireInvCount;

}
