package com.ecommerce.webapp.sportyshoesapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EntityScan(basePackages={"com.ecommerce.webapp.entity"})
@ComponentScan (basePackages={"com.ecommerce.webapp.controller","com.ecommerce.webapp.exception"})
@EnableJpaRepositories("com.ecommerce.webapp.repository")
@EnableSwagger2
public class SportyShoesAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportyShoesAppApplication.class, args);
	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce.webapp"))
				.build();
	}

}