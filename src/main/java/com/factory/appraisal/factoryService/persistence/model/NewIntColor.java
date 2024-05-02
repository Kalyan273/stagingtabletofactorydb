package com.factory.appraisal.factoryService.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "new_int_color",schema = "marketcheck")
public class NewIntColor {
    @Id
    private String interiorColor;

}
