package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FilterDropdowns extends Response {
    private List<String> make;
    private List<String> model;
    private List<String> series;
    private List<Integer> year;
    private List<String> engine;
    private List<String> transmission;

}
