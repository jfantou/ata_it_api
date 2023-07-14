package com.atait.api.mapper;

import com.atait.api.config.ConfigData;
import com.atait.api.model.SalaryInformation;
import com.atait.api.dto.SalaryInformationJson;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class SalaryInformationMapper {
    private static final Logger LOG
            = Logger.getLogger(String.valueOf(ConfigData.class));
    private final SimpleDateFormat formatter = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");

    public SalaryInformation salaryInformationJsonToSalaryInformation(SalaryInformationJson salaryInformationJson){
        SalaryInformation.SalaryInformationBuilder builder = SalaryInformation.builder();
        try {
            builder.timestamp(formatter.parse(salaryInformationJson.getTimestamp()))
                    .job_title(salaryInformationJson.getJobTitle())
                    .location(salaryInformationJson.getLocation())
                    .gender(salaryInformationJson.getGender())
                    .employer(salaryInformationJson.getEmployer())
                    .additional_comments(salaryInformationJson.getAdditionalComments());
        } catch (ParseException e) {
            LOG.severe("Error parsing general information: " + e.getMessage());
        }

        try {
            builder.annual_bonus(Optional.ofNullable(Double.valueOf(salaryInformationJson.getAnnualBonus())).orElse(0.0));
        } catch (NumberFormatException e) {
            LOG.severe("Error parsing the annual bonus: " + e.getMessage());
        }

        try {
            builder.salary(Optional.ofNullable(Integer.valueOf(salaryInformationJson.getSalary())).orElse(0));
        } catch (NumberFormatException e) {
            LOG.severe("Error parsing the salary: " + e.getMessage());
        }

        try {
            builder.annual_stock_value_bonus(Optional.ofNullable(Double.valueOf(salaryInformationJson.getAnnualStockValueBonus())).orElse(0.0));
        } catch (NumberFormatException e) {
            LOG.severe("Error parsing the annual stock value bonus: " + e.getMessage());
        }

        try {
            builder.signing_bonus(Optional.ofNullable(Double.valueOf(salaryInformationJson.getSigningBonus())).orElse(0.0));
        } catch (NumberFormatException e) {
            LOG.severe("Error parsing the signing bonus: " + e.getMessage());
        }

        try {
            builder.years_of_experience(Optional.ofNullable(Float.valueOf(salaryInformationJson.getYearsOfExperience())).orElse(0.0f));
        } catch (NumberFormatException e) {
            LOG.severe("Error parsing the years of experience: " + e.getMessage());
        }

        try {
            builder.years_at_employer(Optional.ofNullable(Float.valueOf(salaryInformationJson.getYearsAtEmployer())).orElse(0.0f));
        } catch (NumberFormatException e) {
            LOG.severe("Error parsing the years at employer: " + e.getMessage());
        }


        return builder.build();
    }
}
