package com.factory.appraisal.factoryService.mktCheck.model;

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.persistence.model.IdEntity;
import com.factory.appraisal.factoryService.persistence.model.TransactionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;

import javax.persistence.*;

@Entity
@Table(name = "fl_cities",schema = "marketcheck")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@AuditOverrides({
        @AuditOverride(forClass=TransactionEntity.class, name="createdBy"),
        @AuditOverride(forClass=TransactionEntity.class, name="createdOn"),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedBy"),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedOn"),
        @AuditOverride(forClass= IdEntity.class, name="version"),
        @AuditOverride(forClass=IdEntity.class, name="sourceSystem"),
        @AuditOverride(forClass= IdEntity.class, name="valid")
})
@AttributeOverride(name = "id", column = @Column(name = "FL_CITY_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class ECities extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fl_city_id_seq")
    @GenericGenerator(name = "fl_city_id_seq", strategy= AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;
    private String name;

    public ECities(String name) {
        this.name = name;
    }
}
