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

import com.reservation.backend.dao.CompagnieRepository;
import com.reservation.backend.entities.Compagnie;
import com.reservation.backend.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CompagnieController {
	
	@Autowired
	CompagnieRepository repository;
	
	@GetMapping("/compagnie")
	public List<Compagnie> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<Compagnie> clients = new ArrayList<>();
		repository.findAll().forEach(clients::add);
		return clients;
	}
	
	@GetMapping("/compagnie/{id}")
	public ResponseEntity<Compagnie> getClientById(@PathVariable(value = "id") Long compagnieId)
		throws ResourceNotFoundException {
		Compagnie compagnie = repository.findById(compagnieId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + compagnieId ));
		
		return ResponseEntity.ok().body(compagnie);
	}
	
	@PostMapping("/compagnie")
	public Compagnie createClient(@RequestBody Compagnie compagnie) {
		return repository.save(compagnie);
	}
	
	@DeleteMapping("/compagnie/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long compagnieId)
			throws ResourceNotFoundException {
		Compagnie compagnie = repository.findById(compagnieId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + compagnieId));
		
		repository.delete(compagnie);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/compagnie/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		repository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/compagnie/{id}")
	public ResponseEntity<Compagnie> updateClient(@PathVariable("id") long id, @RequestBody Compagnie client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Compagnie> clientInfo = repository.findById(id);
		
		if(clientInfo.isPresent()) {
			Compagnie client1 = clientInfo.get();
			client1.setNomCompagnie(client.getNomCompagnie());
			client1.setTel(client.getTel());
			client1.setDestination(client.getDestination());
			client1.setVoyages(client.getVoyages());
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	
}
