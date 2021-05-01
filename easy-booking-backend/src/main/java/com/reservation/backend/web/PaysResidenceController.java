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

import com.reservation.backend.dao.PaysResidenceRepository;
import com.reservation.backend.entities.PaysResidence;
import com.reservation.backend.exception.ResourceNotFoundException;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PaysResidenceController {
	
	@Autowired
	PaysResidenceRepository repository;
	
	@GetMapping("/paysResidence")
	public List<PaysResidence> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<PaysResidence> clients = new ArrayList<>();
		repository.findAll().forEach(clients::add);
		return clients;
	}
	
		
	
	@GetMapping("/paysResidence/{id}")
	public ResponseEntity<PaysResidence> getClientById(@PathVariable(value = "id") Long PaysResidenceId)
		throws ResourceNotFoundException {
		PaysResidence paysResidence = repository.findById(PaysResidenceId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + PaysResidenceId ));
		
		return ResponseEntity.ok().body(paysResidence);
	}
	
	@PostMapping("/paysResidence")
	public PaysResidence createClient(@RequestBody PaysResidence paysResidence) {
		return repository.save(paysResidence);
	}
	
	@DeleteMapping("/paysResidence/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long PaysResidenceId)
			throws ResourceNotFoundException {
		PaysResidence paysResidence = repository.findById(PaysResidenceId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + PaysResidenceId));
		
		repository.delete(paysResidence);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/paysResidence/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		repository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("paysResidence/{id}")
	public ResponseEntity<PaysResidence> updateClient(@PathVariable("id") long id, @RequestBody PaysResidence client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<PaysResidence> clientInfo = repository.findById(id);
		
		if(clientInfo.isPresent()) {
			PaysResidence client1 = clientInfo.get();
			client1.setNomPays(client.getNomPays());	
			
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
