package com.reservation.backend.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Compagnie {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nomCompagnie;
	private String tel;
	
	@ManyToOne
	//@JsonBackReference
	private PaysResidence paysResidence;
	
	@ManyToOne
	@JsonBackReference
	private Destination destination;
		
	@OneToMany(mappedBy="compagnie")
	//@JsonManagedReference
	private Collection<Voyage> voyages;
	
}
