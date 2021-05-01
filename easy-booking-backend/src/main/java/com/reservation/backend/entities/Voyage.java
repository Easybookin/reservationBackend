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
public class Voyage {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String dateVoyage;
	private String heureDepart;
	private String heureRDV;
	private Boolean etat; 
	private String lieuVoyage;
	
	@ManyToOne
	@JsonBackReference
	@JsonProperty(access=Access.WRITE_ONLY)
	private Compagnie compagnie;	
	
	@OneToMany(mappedBy="voyage")
	//@JsonManagedReference
	@JsonProperty(access=Access.WRITE_ONLY)
	private Collection<Ticket> tickets;
	

}
