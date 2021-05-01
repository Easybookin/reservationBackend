package com.reservation.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class GlobalRepositoryRestConfigurer implements RepositoryRestConfigurer{
	
	@SuppressWarnings("deprecation")
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		
		config.setReturnBodyOnCreate(true);
		config.setReturnBodyOnUpdate(true);
		config.exposeIdsFor(com.reservation.backend.entities.PaysResidence.class,com.reservation.backend.entities.Nature.class,com.reservation.backend.entities.Compagnie.class,com.reservation.backend.entities.Destination
				.class,com.reservation.backend.entities.Ticket.class,com.reservation.backend.entities.Voyage.class,com.reservation.backend.entities.Client.class);
		
		config.getCorsRegistry()
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowedMethods("OPTIONS","HEAD","GET","PUT","POST","DELETE","PATCH");
		
		
		
		
		
		
		
	} 
	
	
	
	
}
