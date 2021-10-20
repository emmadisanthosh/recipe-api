package com.recipe.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapperConfiguration for parse DTOs into entities and entities to DTO
 * 
 * @author saemmadi
 *
 */
@Configuration
public class ModelMapperConfiguration {

	/**
	 * @return Modelmapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
