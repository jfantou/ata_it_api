package com.atait.api.controller;

import com.atait.api.exception.FilterNotFoundException;
import com.atait.api.model.SalaryInformation;
import com.atait.api.service.SalaryService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/job_data")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping
    @ResponseBody
    private MappingJacksonValue filterJobData(
            @RequestParam(required = false, name = "fields") Set<String> fields,
            @RequestParam(required = false, name = "sort") List<String> sort,
            @RequestParam(required = false, name = "sort_type") String sortType,
            @RequestParam(required = false, name = "filters") List<String>  filters) {
            if(fields != null && !fields.isEmpty()){
                for(String field: fields){
                    if(!(field.equalsIgnoreCase("salary")||field.equalsIgnoreCase("job_title")||field.equalsIgnoreCase("gender")))
                        throw new FilterNotFoundException(field);
                }
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(salaryService.filterJobData(sort, sortType, filters));
                SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
                FilterProvider filterFields = new SimpleFilterProvider().addFilter("SalaryInformation", filter);
                mappingJacksonValue.setFilters(filterFields);
                return mappingJacksonValue;
            } else {
                return new MappingJacksonValue(salaryService.filterJobData(sort, sortType, filters));
            }
    }

}
