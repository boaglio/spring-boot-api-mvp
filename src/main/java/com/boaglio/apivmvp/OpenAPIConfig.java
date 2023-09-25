package com.boaglio.apivmvp;


import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


    @Bean
    public GroupedOpenApi usersGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi
                .builder()
                .group("clientes")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Clientes API").version(appVersion)))
                .packagesToScan("com.boaglio")
                .build();
    }
}
