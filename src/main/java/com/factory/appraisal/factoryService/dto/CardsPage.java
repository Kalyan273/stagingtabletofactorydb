package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.List;

/**
 * This class is a DTO for showing Pagination details
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardsPage extends Response{

    List<AppraisalVehicleCard> cards;
    private String roleType;
    private String roleGroup;
    private Integer totalPages;
    private Long totalRecords;

}
