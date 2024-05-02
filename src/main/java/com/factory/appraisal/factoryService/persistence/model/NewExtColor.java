package com.factory.appraisal.factoryService.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "new_ext_color",schema = "marketcheck")
public class NewExtColor {
    @Id
    private String exteriorColor;
}
