package com.factory.appraisal.factoryService.dto;


import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PdfList {

    private Long id;
    private String fileName;
    private String module;
    private String status;
}
