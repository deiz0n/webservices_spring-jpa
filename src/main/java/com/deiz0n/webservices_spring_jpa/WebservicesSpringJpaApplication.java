package com.deiz0n.webservices_spring_jpa;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//OpenAPIDefinition(info = @Info(title = "WebServices Spring JPA", version = "1", description = "API desenvolvida para realizar o gerenciamento de pedidos"))
public class WebservicesSpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebservicesSpringJpaApplication.class, args);
    }

}
