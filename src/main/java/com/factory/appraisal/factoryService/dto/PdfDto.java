package com.factory.appraisal.factoryService.dto;


import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PdfDto extends Response {

    private List<PdfList> pdflist;

}
