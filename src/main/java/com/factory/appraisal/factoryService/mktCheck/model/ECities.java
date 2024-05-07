package com.factory.appraisal.factoryService.mktCheck.model;

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "fl_city",schema = "marketcheck")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
public class ECities {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fl_city_id_seq")
    @GenericGenerator(name = "fl_city_id_seq", strategy= AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;
    private String name;

    public ECities(String name) {
        this.name = name;
    }
}
