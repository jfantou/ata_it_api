package com.atait.api.controller;

import com.atait.api.dto.SalaryInformation;
import com.atait.api.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/job_data")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping
    @ResponseBody
    private List<SalaryInformation> filterJobData(
            @RequestParam(required = false, name = "sort") List<String> sort,
            @RequestParam(required = false, name = "sort_type") String sortType,
            @RequestParam(required = false, name = "filters") List<String>  filters) {
            return salaryService.filterJobData(sort, sortType, filters);
    }

}
