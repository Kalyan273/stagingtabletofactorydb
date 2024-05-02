package com.factory.appraisal.factoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FactoryReport {

    private String memberId;
    private String memberName;
    private String location;
    private String signUpDate;
    private String searchby;
    private String memberType;
    private String factorySalesMan;
    private String factoryMgr;
    private String totalRev;

}
