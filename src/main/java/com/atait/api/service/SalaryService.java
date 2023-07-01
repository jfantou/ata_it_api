package com.atait.api.service;
import com.atait.api.config.ConfigData;
import com.atait.api.dto.SalaryInformation;
import com.atait.api.exception.FilterNotFoundException;
import com.atait.api.utils.SalaryInformationPredicate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.atait.api.utils.SalaryComparator.salaryComparator;


@Service
public class SalaryService {

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(SalaryService.class));
    public static List<SalaryInformation> data = ConfigData.listData;

    public  List<SalaryInformation> filterJobData(List<String> sortList, String sortType, List<String> filters) throws FilterNotFoundException {

        List<SalaryInformation> filteredData = data;

        if(filters != null ){
            filteredData = filteredData.stream().filter(getPredicate(filters)).collect(Collectors.toList());
        }

        if (sortList != null) {
            filteredData = sortData(sortList, sortType, filteredData);
        }

        filteredData.stream().sorted();

        return filteredData;
    }

    private List<SalaryInformation> sortData(List<String> sortList, String sortType, List<SalaryInformation> data) {
        List<SalaryInformation> sortData;
        Comparator<SalaryInformation> comparator;
        if(sortList.size() == 1){
            comparator = salaryComparator(sortList.get(0), sortType);
        } else {
            comparator = salaryComparator(sortList.get(0), sortType);
            for (String sort : sortList) {
                comparator = comparator.thenComparing(salaryComparator(sort, sortType));
            }   
        }
        sortData = data.stream().sorted(comparator).collect(Collectors.toList());
        return sortData;
    }

    private Predicate<SalaryInformation> getPredicate(List<String> filters) throws FilterNotFoundException {
        Predicate<SalaryInformation> predicate;
        if(filters.size() == 1){
            Optional<Predicate<SalaryInformation>> predicateGenerate = SalaryInformationPredicate.filter(filters.get(0));
            if(predicateGenerate.isPresent()){
                predicate = predicateGenerate.get();
            } else {
                throw new FilterNotFoundException(filters.get(0));
            }
        } else {
            predicate = constructPredicate(filters);
        }

        return predicate;
    }

    private Predicate<SalaryInformation> constructPredicate(List<String> filters) {
        Predicate<SalaryInformation> predicate;
        Optional<Predicate<SalaryInformation>> predicateGenerate = SalaryInformationPredicate.filter(filters.get(0));
        if(predicateGenerate.isPresent()){
            predicate = predicateGenerate.get();
        } else {
            throw new FilterNotFoundException(filters.get(0));
        }
        for (String filter : filters) {
            predicateGenerate = SalaryInformationPredicate.filter(filter);
            if(predicateGenerate.isPresent()){
                predicate = predicate.and(predicateGenerate.get());
            } else {
                throw new FilterNotFoundException(filter);
            }
        }
        return predicate;
    }

}
