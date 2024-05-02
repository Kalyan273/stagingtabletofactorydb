package com.factory.appraisal.factoryService.dto;


import io.swagger.annotations.ApiModel;
import lombok.*;



@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@ApiModel(description = "Factory Training Portal")
public class FtryTraining  {

//    private Long id;
    private String title;
    private String description;
    private String video;




}
