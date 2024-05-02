package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DlrList extends Response {

    List<DealerRegistration> dlrWithNoCmp;
    private Integer totalPages;
    private Long totalRecords;
}
