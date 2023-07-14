package com.atait.api.controller;

import com.atait.api.model.SalaryInformation;
import com.atait.api.exception.FilterNotFoundException;
import com.atait.api.exception.SortNotFoundException;
import com.atait.api.service.SalaryService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SalaryControllerTest {
    @Mock
    private SalaryService service;

    @InjectMocks
    SalaryController SalaryControllerTest;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(SalaryControllerTest).build();

    }
    @Test
    public void SalaryControllerShouldReturnDataFromService() throws Exception {
        List<SalaryInformation> listMockData = Arrays.asList(SalaryInformation.builder()
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
                .build());
        List<String> filters = Arrays.asList("salary>500");
        when(service.filterJobData(null,null,filters)).thenReturn(listMockData);
        this.mockMvc.perform(get("/job_data?filters=salary>500")).andExpect(status().isOk())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    public void SalaryControllerShouldReturnFiltersException() throws Exception {
        List<String> filters = Arrays.asList("signingBonus>500");
        when(service.filterJobData(null,null,filters)).thenThrow(FilterNotFoundException.class);
        this.mockMvc.perform(get("/job_data?filters=signingBonus>500")).andExpect(status().isNotFound());
    }

    @Test
    public void SalaryControllerShouldReturnSortException() throws Exception {
        List<String> sort = Arrays.asList("signingBonus>500");
        when(service.filterJobData(sort,null,null)).thenThrow(SortNotFoundException.class);
        this.mockMvc.perform(get("/job_data?sort=signingBonus>500")).andExpect(status().isNotFound());
    }

    @Test
    public void SalaryControllerShouldReturnOK() throws Exception {
        List<String> filters = Arrays.asList("salary>500");
        when(service.filterJobData(null,null,filters)).thenThrow(SortNotFoundException.class);
        this.mockMvc.perform(get("/job_data?sort=signingBonus>500")).andExpect(status().isOk());
    }

}
