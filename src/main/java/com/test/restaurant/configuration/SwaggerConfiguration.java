package com.test.restaurant.configuration;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDateTime;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Restaurant.class, Reservation.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.restaurant.controller"))
                .paths(PathSelectors.any())
                .build()
                ;
    }
}
