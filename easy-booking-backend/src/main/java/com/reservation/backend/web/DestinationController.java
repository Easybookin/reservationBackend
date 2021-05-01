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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.backend.dao.DestinationRepository;
import com.reservation.backend.entities.Destination;
import com.reservation.backend.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DestinationController {
	
	
	@Autowired
	private DestinationRepository repository;
	
	@GetMapping("/destination")
	public List<Destination> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<Destination> clients = new ArrayList<>();
		repository.findAll().forEach(clients::add);
		return clients;
	}
	
	@GetMapping("/destination/{id}")
	public ResponseEntity<Destination> getClientById(@PathVariable(value = "id") Long destinationId)
		throws ResourceNotFoundException {
		Destination destination = repository.findById(destinationId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + destinationId ));
		
		return ResponseEntity.ok().body(destination);
	}
	
	@PostMapping("/destination")
	public Destination createClient(@RequestBody Destination destination) {
		return repository.save(destination);
	}
	
	@DeleteMapping("/destination/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long destinationId)
			throws ResourceNotFoundException {
		Destination destination = repository.findById(destinationId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + destinationId));
		
		repository.delete(destination);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/destination/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		repository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/destination/{id}")
	public ResponseEntity<Destination> updateClient(@PathVariable("id") long id, @RequestBody Destination client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Destination> clientInfo = repository.findById(id);
		
		if(clientInfo.isPresent()) {
			Destination client1 = clientInfo.get();
			client1.setNomDestination(client.getNomDestination());	
			client1.setCompagnies(client.getCompagnies());
			client1.setPaysResidence(client.getPaysResidence());
			//client1.setNature(client.getNature());
			
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
/*	
	@GetMapping("/desti/dest/{a}/{b}")
	public List<Destination> listDest(@PathVariable PaysResidence a, @PathVariable Nature b){
		
		return repository.findByDest(a,b);
			 
	}
	
	
	@GetMapping("/desti/dest/{a}/{b}/{id}")
	public List<Destination> listDest(@PathVariable PaysResidence a, @PathVariable Nature b, @PathVariable("id") long id){
		
		return repository.findByDesCompagnie(a, b, id);
			 
	}
	
*/	
	
	
	
}
