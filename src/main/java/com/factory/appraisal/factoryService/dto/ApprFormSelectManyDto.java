package com.factory.appraisal.factoryService.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApprFormSelectManyDto {
    private String vehDrWarnLightSts;
    private String apprVehAcCondn;
    private String apprVehStereoSts;
    private String apprVehInteriCondn;
    private String apprEnginePer;
    private String apprVehOilCondn;
    private String  apprBrakingSysSts;
    private String apprTransmissionSts;
    private String steeringFeel;
    private String apprVehTireCondn;
    private String bookAndKeys;
    private String rearWindow;
}
