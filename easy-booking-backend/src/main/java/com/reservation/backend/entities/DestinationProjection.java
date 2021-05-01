package com.reservation.backend.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection (name="c1", types= {Destination.class})
public interface DestinationProjection {
	
	public Long getId();
	public String getNomDestination();
	public PaysResidence getPaysResidence();

}
