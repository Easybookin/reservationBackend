package com.reservation.backend.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ticket {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int prix;
	private int nbreTicket;

	@ManyToOne
	@JsonProperty(access=Access.WRITE_ONLY) 
	//@JsonBackReference
	private Voyage voyage;
	
	@ManyToOne
	//@JsonBackReference
	private PaysResidence paysResidence;
	
	@ManyToOne
	//@JsonBackReference
	private Destination destination;
	
	@ManyToOne
	@JsonBackReference
	private Compagnie compagnie;
	
	@OneToMany(mappedBy="ticket")
	//@JsonManagedReference
	private Collection<Client> clients;

}
