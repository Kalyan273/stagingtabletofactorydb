package com.factory.appraisal.factoryService.dto;


import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellingDealerList extends Response {
    List<SellingDealer> slrDlrList;


}
