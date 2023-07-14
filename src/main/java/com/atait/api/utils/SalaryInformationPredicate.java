package com.atait.api.utils;

import com.atait.api.model.SalaryInformation;
import com.atait.api.exception.SalaryParsingException;

import java.util.Optional;
import java.util.function.Predicate;

public class SalaryInformationPredicate {

    public static Optional<Predicate<SalaryInformation>> filter(String filter) {
        Predicate<SalaryInformation> predicate = null;
        if(filter.contains("salary")){
            predicate = salaryPredicate(filter);
        }

        if(filter.contains("job_title")){
            predicate = p ->p.getJob_title().equalsIgnoreCase(filter.substring(10));
        }

        if(filter.contains("gender")){
            predicate = p ->p.getGender().equalsIgnoreCase(filter.substring(7));
        }

        return Optional.ofNullable(predicate);
    }

    public static Predicate<SalaryInformation> salaryPredicate(String filter){
        Predicate<SalaryInformation> salaryPredicate = null;

        if(filter.contains(">=")) {
            return p -> p.getSalary() >= getSalary(8, filter);
        } else if (filter.contains(">")){
            return p -> p.getSalary() > getSalary(7, filter);
        }

        if(filter.contains("<=")){
            return p -> p.getSalary() <= getSalary(8, filter);
        } else if(filter.contains("<")){
            return p -> p.getSalary() < getSalary(7, filter);
        }

        if(filter.contains("=")){
            return p -> p.getSalary() == getSalary(7, filter);
        }

        return salaryPredicate;
    }

    public static int getSalary(int index, String filter){
        try {
            return Integer.valueOf(filter.substring(index));
        } catch (NumberFormatException e) {
            throw new SalaryParsingException(filter);
        }
    }

}
