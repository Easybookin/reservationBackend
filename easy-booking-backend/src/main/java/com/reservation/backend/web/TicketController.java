package com.reservation.backend.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.backend.dao.ClientRepository;
import com.reservation.backend.dao.TicketRepository;
import com.reservation.backend.entities.Client;
import com.reservation.backend.entities.Ticket;
import com.reservation.backend.exception.ResourceNotFoundException;

import lombok.Data;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TicketController {
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	
	
	@GetMapping("/ticket")
	public List<Ticket> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<Ticket> clients = new ArrayList<>();
		ticketRepository.findAll().forEach(clients::add);
		return clients;
	}
	
	@GetMapping("/ticket/{id}")
	public ResponseEntity<Ticket> getClientById(@PathVariable(value = "id") Long tikectId)
		throws ResourceNotFoundException {
		Ticket tikect = ticketRepository.findById(tikectId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + tikectId ));
		
		return ResponseEntity.ok().body(tikect);
	}
	
	@PostMapping("/ticket")
	public Ticket createClient(@RequestBody Ticket tikect) {
		return ticketRepository.save(tikect);
	}
	
	@DeleteMapping("/ticket/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long tikectId)
			throws ResourceNotFoundException {
		Ticket ticket = ticketRepository.findById(tikectId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + tikectId));
		
		ticketRepository.delete(ticket);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/ticket/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		ticketRepository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/ticket/{id}")
	public ResponseEntity<Ticket> updateClient(@PathVariable("id") long id, @RequestBody Ticket client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Ticket> clientInfo = ticketRepository.findById(id);
		
		if(clientInfo.isPresent()) {
			Ticket client1 = clientInfo.get();
			client1.setPrix(client.getPrix());
			client1.setNbreTicket(client.getNbreTicket());
			client1.setClients(client.getClients());
			
			return new ResponseEntity<>(ticketRepository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/payerTickets")
	@javax.transaction.Transactional
	public void payerTickets(@RequestBody TicketForm ticketForm){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket = ticketRepository.findById(idTicket).get();
			Client client = new Client();
			
			client.setNomClient(ticketForm.getNomClient());
			client.setTelClient(ticketForm.getTelClient());
			client.setEmail(ticketForm.getEmail());
			client.setTraiter(false);
			client.setNbreReservationTicket(ticketForm.getNbreReservationTicket());
			client.setTicket(ticket);
			

			clientRepository.save(client);
			
			ticket.setNbreTicket(ticket.getNbreTicket() - client.getNbreReservationTicket());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
			
				
		});
		
		System.out.println("Votre nom est : "+ticketForm.getNomClient() +"\n"+ 
				"Votre numéro de Téléphone est : "+ticketForm.getTelClient()+"\n"+ 
				"Vous avez réservé " + ticketForm.getNbreReservationTicket() +" billet(s)\n"+
				"date de réservation est "
				+dateFormat.format(date)+"\n"+ "L'IDENTIFICATION DU VOYAGE : " +
				ticketForm.getTickets());
		
		//return listTickets;
		
		
	}
	
}

@Data
class TicketForm{
	private String nomClient;
	private String telClient;
	private int nbreReservationTicket;
	private String email;
	private List<Long> tickets=new ArrayList<>();
}
