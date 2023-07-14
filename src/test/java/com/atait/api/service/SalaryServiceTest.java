package com.atait.api.service;

import com.atait.api.model.SalaryInformation;
import com.atait.api.exception.FilterNotFoundException;
import com.atait.api.exception.SortNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class SalaryServiceTest {

    SalaryService salaryService = new SalaryService();

    @Before
    public void initData(){
        salaryService.data = Arrays.asList(SalaryInformation.builder()
                        .years_of_experience((float) 1.1)
                        .years_at_employer((float)1.5)
                        .signing_bonus(1000)
                        .salary(1000)
                        .annual_stock_value_bonus(1000)
                        .annual_bonus(1000)
                        .additional_comments("")
                        .employer("testEmployer")
                        .gender("M")
                        .job_title("Software engineer")
                .build(),
                SalaryInformation.builder()
                        .years_of_experience((float) 1.1)
                        .years_at_employer((float)1.5)
                        .signing_bonus(500)
                        .salary(500)
                        .annual_stock_value_bonus(1000)
                        .annual_bonus(1000)
                        .additional_comments("")
                        .employer("testEmployer")
                        .gender("F")
                        .job_title("Devops engineer")
                        .build(),
                SalaryInformation.builder()
                        .years_of_experience((float) 1.1)
                        .years_at_employer((float)1.5)
                        .signing_bonus(400)
                        .salary(400)
                        .annual_stock_value_bonus(1000)
                        .annual_bonus(1000)
                        .additional_comments("")
                        .employer("testEmployer")
                        .gender("F")
                        .job_title("Backend engineer")
                        .build(),
                SalaryInformation.builder()
                        .years_of_experience((float) 1.1)
                        .years_at_employer((float)1.5)
                        .signing_bonus(400)
                        .salary(400)
                        .annual_stock_value_bonus(1000)
                        .annual_bonus(1000)
                        .additional_comments("")
                        .employer("testEmployer")
                        .gender("M")
                        .job_title("Frontend engineer")
                        .build()
                );
    }

    @Test
    public void filterOneColumn(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("salary>500");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 1);
    }

    @Test
    public void filterByJobTitle(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("job_title=Software engineer");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 1);
    }

    @Test
    public void filterByGenderTitle(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("gender=F");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 2);
    }

    @Test
    public void filterBySalaryEqualsTo500(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("salary=500");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 1);
    }

    @Test
    public void filterBySalaryGreaterThanOrEqualsTo500(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("salary>=500");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 2);
    }

    @Test
    public void filterBySalaryLessThanOrEqualsTo500(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("salary<=500");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 3);
    }

    @Test
    public void filterMultipleColumn(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("salary<1000", "job_title=Devops engineer");
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.size() == 1);
    }

    @Test
    public void sortOneColumn(){
        List<String> sort = Arrays.asList("salary");
        List<String> filters = null;
        String sortType = null;
        Comparator<SalaryInformation> compare = Comparator
                .comparing(SalaryInformation::getSalary);
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        List<SalaryInformation> expected = salaryService.data.stream().sorted(compare).collect(Collectors.toList());
        assertTrue(rst.get(0).getJob_title() == "Backend engineer");
        assertTrue(rst.get(1).getJob_title() == "Frontend engineer");
        assertTrue(rst.get(2).getJob_title() == "Devops engineer");
        assertTrue(rst.get(3).getJob_title() == "Software engineer");
    }

    @Test
    public void sortMultipleColumn(){
        List<String> sort = Arrays.asList("salary", "gender");
        List<String> filters = null;
        String sortType = null;
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.get(0).getJob_title() == "Backend engineer");
        assertTrue(rst.get(1).getJob_title() == "Frontend engineer");
        assertTrue(rst.get(2).getJob_title() == "Devops engineer");
        assertTrue(rst.get(3).getJob_title() == "Software engineer");
    }

    @Test
    public void sortDescOneColumn(){
        List<String> sort = Arrays.asList("salary");
        List<String> filters = null;
        String sortType = "desc";
        List<SalaryInformation> rst = salaryService.filterJobData(sort, sortType, filters);
        assertTrue(rst.get(3).getJob_title() == "Frontend engineer");
        assertTrue(rst.get(2).getJob_title() == "Backend engineer");
        assertTrue(rst.get(1).getJob_title() == "Devops engineer");
        assertTrue(rst.get(0).getJob_title() == "Software engineer");

    }

    @Test
    public void filterColumnNotAvailable(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("signingBonus>12");
        String sortType = null;

        Exception exception = assertThrows(FilterNotFoundException.class, () -> {
            salaryService.filterJobData(sort, sortType, filters);
        });

        String expectedMessage = "We can't filter this column (filter not available): signingBonus>12";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void filterColumnNotAvailable2(){
        List<String> sort = null;
        List<String> filters = Arrays.asList("salary>12", "signingBonus>12");
        String sortType = null;

        Exception exception = assertThrows(FilterNotFoundException.class, () -> {
            salaryService.filterJobData(sort, sortType, filters);
        });

        String expectedMessage = "We can't filter this column (filter not available): signingBonus>12";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void sortNotAvailableForWrongColumn(){
        List<String> sort = Arrays.asList("wrongColumn");
        List<String> filters = null;
        String sortType = null;

        Exception exception = assertThrows(SortNotFoundException.class, () -> {
            salaryService.filterJobData(sort, sortType, filters);
        });

        String expectedMessage = "This column wrongColumn don't exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}
