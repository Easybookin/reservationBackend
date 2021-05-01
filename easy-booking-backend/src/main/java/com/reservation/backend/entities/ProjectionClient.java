package com.reservation.backend.entities;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

@Projection (name="c1", types= {Ticket.class})
public interface ProjectionClient {
	
	
	public Long getId();
	
	public int getPrix();
	public int getNbreTicket();
	
	public Voyage getVoyage();
	
	public PaysResidence getPaysResidence();
	
	public Destination getDestination();
	
	public Compagnie getCompagnie();
	
	public Collection<Client> getClients();

	
	
	

}
