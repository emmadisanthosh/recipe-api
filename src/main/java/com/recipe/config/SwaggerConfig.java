package com.recipe.config;

import static com.recipe.util.Constants.API_DESCRIPTION;
import static com.recipe.util.Constants.API_TITLE;
import static com.recipe.util.Constants.API_VERSION;
import static com.recipe.util.Constants.LICENSE;
import static com.recipe.util.Constants.LICENSE_URL;
import static com.recipe.util.Constants.TERM_OF_SERVICE;
import static com.recipe.util.Constants.CONTACT_NAME;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class is used for Swagger Documentation
 * 
 * @author saemmadi
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Method to generate Swagger documentation for api.
	 * 
	 * @return Docket class object
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.recipe.controller")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(API_TITLE, API_DESCRIPTION, API_VERSION, TERM_OF_SERVICE, CONTACT_NAME, LICENSE,
				LICENSE_URL);
		return apiInfo;
	}

}
