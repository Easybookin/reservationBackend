package com.reservation.backend.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection (name="c1", types= {Compagnie.class})
public interface CompagnieProjection {

	public Long getId();
	public String getNomCompagnie();
	
	public String getTel();
	public String reference();
	
	public PaysResidence getPaysResidence();
	public Destination getDestination();
}
