package com.atait.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryInformation {
    @JsonIgnore
    private Date timestamp;

    @JsonIgnore
    private String employer;

    @JsonIgnore
    private String location;

    private String job_title;

    @JsonIgnore
    private float years_at_employer;

    @JsonIgnore
    private float years_of_experience;

    private int salary;

    @JsonIgnore
    private double signing_bonus;

    @JsonIgnore
    private double annual_bonus;

    @JsonIgnore
    private double annual_stock_value_bonus;

    private String gender;

    @JsonIgnore
    private String additional_comments;
}
