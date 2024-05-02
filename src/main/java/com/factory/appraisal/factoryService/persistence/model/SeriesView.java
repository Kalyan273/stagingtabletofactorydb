package com.factory.appraisal.factoryService.persistence.model;


import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Data
public class SeriesView {
    @Id
    private String series;
}
