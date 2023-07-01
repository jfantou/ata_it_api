package com.atait.api.config;

import com.atait.api.dto.SalaryInformation;
import com.atait.api.mapper.SalaryInformationMapper;
import com.atait.api.model.SalaryInformationJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ConfigData {

    public static List<SalaryInformation> listData;

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(ConfigData.class));
    
    @Autowired
    SalaryInformationMapper salaryInformationMapper;
    
    @PostConstruct
    private void initData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LOG.info("Begin of init data");
            String file = new String(Files.readAllBytes(Paths.get("src/main/resources/data/salary_survey-3.json")));
            List<SalaryInformationJson> data = List.of(mapper.readValue(file, SalaryInformationJson[].class));
            listData = data.stream().map(salary -> salaryInformationToSalaryInformationDto(salary)).collect(Collectors.toList());
            LOG.info("End of init data");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SalaryInformation salaryInformationToSalaryInformationDto (SalaryInformationJson salaryInformationJson){
        return salaryInformationMapper.salaryInformationJsonToSalaryInformation(salaryInformationJson);
    }


}
