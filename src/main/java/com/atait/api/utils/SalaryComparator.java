package com.atait.api.utils;


import com.atait.api.dto.SalaryInformation;
import com.atait.api.exception.SortNotFoundException;

import java.util.Comparator;

public class SalaryComparator {

    static Comparator<SalaryInformation> salaryComparator = Comparator.comparing(SalaryInformation::getSalary);
    static Comparator<SalaryInformation> timestampComparator = Comparator.comparing(SalaryInformation::getTimestamp);

    static Comparator<SalaryInformation> employerComparator = Comparator.comparing(SalaryInformation::getEmployer);

    static Comparator<SalaryInformation> locationComparator = Comparator.comparing(SalaryInformation::getLocation);

    static Comparator<SalaryInformation> jobTitleComparator = Comparator.comparing(SalaryInformation::getJob_title);

    static Comparator<SalaryInformation> yearsAtEmployerComparator = Comparator.comparingDouble(SalaryInformation::getYears_at_employer);

    static Comparator<SalaryInformation> yearsOfExperienceComparator = Comparator.comparingDouble(SalaryInformation::getYears_of_experience);

    static Comparator<SalaryInformation> signingBonusComparator = Comparator.comparingDouble(SalaryInformation::getSigning_bonus);

    static Comparator<SalaryInformation> annualBonusComparator = Comparator.comparingDouble(SalaryInformation::getAnnual_bonus);

    static Comparator<SalaryInformation> annualStockValueBonusComparator = Comparator.comparingDouble(SalaryInformation::getAnnual_stock_value_bonus);

    static Comparator<SalaryInformation> genderComparator = Comparator.comparing(SalaryInformation::getGender);

    static Comparator<SalaryInformation> additionalCommentsComparator = Comparator.comparing(SalaryInformation::getAdditional_comments);


    public static Comparator<SalaryInformation> salaryComparator(String sort, String sortType) {
        Comparator<SalaryInformation> comparator;
        switch(sort) {
            case "salary" -> comparator = salaryComparator;
            case "timestamp" -> comparator = timestampComparator;
            case "employer" -> comparator = employerComparator;
            case "location" -> comparator = locationComparator;
            case "job_title" -> comparator = jobTitleComparator;
            case "years_at_employer" -> comparator = yearsAtEmployerComparator;
            case "years_of_experience" -> comparator = yearsOfExperienceComparator;
            case "signing_bonus" -> comparator = signingBonusComparator;
            case "annual_bonus" -> comparator = annualBonusComparator;
            case "annual_stock_value_bonus" -> comparator = annualStockValueBonusComparator;
            case "gender" -> comparator = genderComparator;
            case "additional_comments" -> comparator = additionalCommentsComparator;
            default -> throw new SortNotFoundException(sort);
        }

        if (sortType != null && !sortType.isBlank() && "desc".equalsIgnoreCase(sortType))
            return comparator.reversed();

        return comparator;
    }
}
