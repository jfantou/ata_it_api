package com.atait.api.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFilter("SalaryInformation")
public class SalaryInformation {
    private Date timestamp;

    private String employer;

    private String location;

    private String job_title;

    private float years_at_employer;

    private float years_of_experience;

    private int salary;

    private double signing_bonus;

    private double annual_bonus;

    private double annual_stock_value_bonus;

    private String gender;

    private String additional_comments;
}
