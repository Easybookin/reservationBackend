package com.reservation.backend.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.backend.dao.ClientRepository;
import com.reservation.backend.entities.Client;
import com.reservation.backend.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	ClientRepository repository;
	
	@GetMapping("/client")
	public List<Client> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<Client> clients = new ArrayList<>();
		repository.findAll().forEach(clients::add);
		return clients; 
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
		throws ResourceNotFoundException {
		Client compagnie = repository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + clientId ));
		
		return ResponseEntity.ok().body(compagnie);
	}
	
	@PostMapping("/client")
	public Client createClient(@RequestBody Client client) {
		return repository.save(client);
	}
	
	@DeleteMapping("/client/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId)
			throws ResourceNotFoundException {
		Client client = repository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + clientId));
		
		repository.delete(client);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/client/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		repository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/client/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Client> clientInfo = repository.findById(id);
		
		if(clientInfo.isPresent()) {
			Client client1 = clientInfo.get();
			client1.setNbreReservationTicket(client.getNbreReservationTicket());
			client1.setNomClient(client.getNomClient());
			client1.setTelClient(client.getTelClient());
			client1.setTicket(client.getTicket());
			client1.setTraiter(client.isTraiter());
			
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PatchMapping("/clientt/{id}")
	public ResponseEntity<Client> updateClients(@PathVariable("id") long id, @RequestBody Client client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Client> clientInfo = repository.findById(id);
		
		if(clientInfo.isPresent()) {
			Client client1 = clientInfo.get();
			client1.setTraiter(client.isTraiter());
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/recherch")
	  public List<Client> parDate(@RequestParam(name="mc", defaultValue= "") String mc ) {
	    System.out.println("... Recherche par date ...");
	   
	    return  repository.cherche("%"+mc+"%");
	   
	  }
	

}
