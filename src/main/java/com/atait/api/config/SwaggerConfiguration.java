package com.atait.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Job data API")
                        .description("For using the filter, you need to write the field name contains in the SalaryInformation schema. You can find the different attributes in the chapter call Schemas below")
                        .version("1.0"));
    }
}