package com.maxel.decrypto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");
    private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m422, m500))
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m404, m422, m500))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.maxel.decrypto.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "API para codificação e descodificação de mensagens",
                "Esta API é de criação própria e serve para cifrar mensagens e para decifrar mensagens que foram cifradas com a mesma lógica",
                "Versão 1.0",
                "https://www.udemy.com/terms",
                new Contact("Maxel Udson", "https://github.com/Maxel-Uds", "maxellopes32@gmail.com"),
                "Uso recomendado para aqueles que tem interesse em criptografia ou apenas por diversão",
                "https://www.udemy.com/terms",
                Collections.emptyList()
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
}
