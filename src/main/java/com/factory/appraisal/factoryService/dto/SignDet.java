package com.factory.appraisal.factoryService.dto;
//@Author:Yudhister vijay

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class is a DTO of SignDet
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignDet {
    private Long signId;
    @NotNull(message = "sign Document should not be null")
    @Size(max = 17 , message = "maximum size of signDocument is 17")
    private String signDocument;



}