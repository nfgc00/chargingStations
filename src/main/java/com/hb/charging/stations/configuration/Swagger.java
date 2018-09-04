package com.hb.charging.stations.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class 
 * 
 * @author fernando
 *
 */
@Configuration
@EnableSwagger2
public class Swagger {
	
	/**
     * Method to generate REST API Swagger documentation
     *
     * @return {@code Docket}
     */
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.hb.charging.stations.rest"))
                .paths(regex("/chargingStation.*"))
                .build().apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "Charging Stations REST API Documentation", 
          "REST API to manage (store, update, list, retrieve by ID and Postal Code, search around a given location) Charging Stations.", 
          "V1.0.0", 
          "Terms of service", 
          new Contact("Fernando Gonzalez", null, "nfgc00@gmail.com"), 
          "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
   }

}
