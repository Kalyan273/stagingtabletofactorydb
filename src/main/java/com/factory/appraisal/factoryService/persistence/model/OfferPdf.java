package com.factory.appraisal.factoryService.persistence.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name ="ofrreport")
@Immutable
@NoArgsConstructor
@Getter
@Setter
public class OfferPdf {
    @Id
    private Long offerId;
    private String vin;
    private Date createdOn;
    private String firstName;
    private String lastName;
    private String userName;
    private Long sellerUserId;
    private Long miles;
    private Long offerCount;

}
