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

import com.reservation.backend.dao.NatureRepository;
import com.reservation.backend.entities.Nature;
import com.reservation.backend.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class NatureController {
	
	@Autowired
	NatureRepository repository;
	
	@GetMapping("/nature")
	public List<Nature> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<Nature> clients = new ArrayList<>();
		repository.findAll().forEach(clients::add);
		return clients;
	}
	
	@GetMapping("/nature/{id}")
	public ResponseEntity<Nature> getClientById(@PathVariable(value = "id") Long NatureId)
		throws ResourceNotFoundException {
		Nature nature = repository.findById(NatureId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + NatureId ));
		
		return ResponseEntity.ok().body(nature);
	}
	
	@PostMapping("/nature")
	public Nature createClient(@RequestBody Nature nature) {
		return repository.save(nature);
	}
	
	@DeleteMapping("/nature/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long NatureId)
			throws ResourceNotFoundException {
		Nature nature = repository.findById(NatureId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + NatureId));
		
		repository.delete(nature);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/nature/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		repository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/nature/{id}")
	public ResponseEntity<Nature> updateClient(@PathVariable("id") long id, @RequestBody Nature client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Nature> clientInfo = repository.findById(id);
		
		if(clientInfo.isPresent()) {
			Nature client1 = clientInfo.get();
			client1.setTypeVoyage(client.getTypeVoyage());	
			
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
