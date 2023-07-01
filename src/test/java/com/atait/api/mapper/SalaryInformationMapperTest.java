package com.atait.api.mapper;

import com.atait.api.dto.SalaryInformation;
import com.atait.api.model.SalaryInformationJson;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class SalaryInformationMapperTest {

    private SalaryInformationMapper salaryInformationMapper = new SalaryInformationMapper();
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/d/yyyy HH:mm:ss");

    @Test
    public void testOk() {
        SalaryInformationJson testData = SalaryInformationJson.builder()
                .additionalComments("test")
                .annualBonus("1000.0")
                .signingBonus("100.0")
                .salary("1000")
                .employer("test")
                .annualStockValueBonus("100.0")
                .gender("M")
                .jobTitle("test")
                .yearsAtEmployer("2")
                .yearsOfExperience("1.5")
                .timestamp("3/21/2016 13:04:42")
                .build();
        salaryInformationMapper.salaryInformationJsonToSalaryInformation(testData);
    }

    @Test
    public void runEvenIfNumberAndStringIsNotGood() throws Exception{
        SalaryInformationJson testData = SalaryInformationJson.builder()
                .additionalComments("")
                .annualBonus("1000,0")
                .location("")
                .signingBonus("100,0")
                .salary("1000,4")
                .employer("test")
                .annualStockValueBonus("100,0")
                .gender("M")
                .jobTitle("")
                .yearsAtEmployer("<1")
                .yearsOfExperience("1,5")
                .timestamp("3/21/2016 13:04:42")
                .build();

        SalaryInformation salaryInformationExpected = SalaryInformation.builder()
                .timestamp(formatter.parse("3/21/2016 13:04:42"))
                .job_title("test")
                .location("test")
                .gender("M")
                .employer("test")
                .additional_comments("test")
                .salary(0)
                .annual_bonus(0.0)
                .annual_stock_value_bonus(0.0)
                .signing_bonus(0.0)
                .years_at_employer(0.0F)
                .years_of_experience(0.0f)
                .build();

        SalaryInformation rst = salaryInformationMapper.salaryInformationJsonToSalaryInformation(testData);

        assertEquals(rst.getSalary(), salaryInformationExpected.getSalary());
        assertTrue(rst.getAnnual_bonus() == salaryInformationExpected.getAnnual_bonus());
        assertTrue(rst.getAnnual_stock_value_bonus() == salaryInformationExpected.getAnnual_stock_value_bonus());
        assertTrue(rst.getYears_at_employer() == salaryInformationExpected.getYears_at_employer());
        assertTrue(rst.getYears_of_experience() == salaryInformationExpected.getYears_of_experience());
        assertTrue(rst.getSigning_bonus() == salaryInformationExpected.getSigning_bonus());
        assertEquals(rst.getGender(), "M");
        assertEquals(rst.getJob_title(), "");
        assertEquals(rst.getLocation(), "");
        assertEquals(rst.getEmployer(), "test");
        assertEquals(rst.getAdditional_comments(), "");

    }


}
