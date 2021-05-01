package com.reservation.backend.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Client {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nomClient;
	private String telClient;
	private String email;
	private int nbreReservationTicket;
	
	private boolean traiter;
	
	private String creationDate;
	
	@ManyToOne
	//@JsonProperty(access=Access.WRITE_ONLY)
	@JsonBackReference
	private Ticket ticket;
	
	
	
	@javax.persistence.PrePersist
	public void initCreationDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");

		this.creationDate = sdf.format(new Date());

		System.out.println(this.creationDate);

	}
	
	
}
