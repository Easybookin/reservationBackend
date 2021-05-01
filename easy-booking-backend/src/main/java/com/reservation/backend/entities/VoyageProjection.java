package com.reservation.backend.entities;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection (name="c1", types= {Voyage.class})
public interface VoyageProjection {
	
	public Long getId();
	public  Date getDateVoyage();
	public String getHeureDepart();
	public String getHeureRDV();
	public Boolean getEtat();
	public String getReference();
	public String getLieuVoyage();
	public Compagnie getCompagnie();
	


}
