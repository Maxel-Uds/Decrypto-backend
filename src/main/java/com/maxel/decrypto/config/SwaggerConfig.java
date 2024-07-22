package com.maxel.decrypto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class SwaggerConfig {


    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("com.maxel.decrypto")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("Decrypto Project - 2024")
                        .contact(new Contact().name("Maxel Udson").url("https://github.com/Maxel-Uds").email("maxellopes32@gmail.com"))
                        .description("Esta é uma API para criptografia e descriptografia de mensagens usando um algoritmo de substituição")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}
