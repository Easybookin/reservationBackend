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

import com.reservation.backend.dao.VoyageRepository;
import com.reservation.backend.entities.Voyage;
import com.reservation.backend.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class VoyageController {
	
	@Autowired
	VoyageRepository repository;
	
	@GetMapping("/voyage")
	public List<Voyage> getAllClient() {
		System.out.println("Get all Artices.....");
		
		List<Voyage> clients = new ArrayList<>();
		repository.findAll().forEach(clients::add);
		return clients;
	}
	
	@GetMapping("/voyage/{id}")
	public ResponseEntity<Voyage> getClientById(@PathVariable(value = "id") Long VoyageId)
		throws ResourceNotFoundException {
		Voyage voyage = repository.findById(VoyageId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + VoyageId ));
		
		return ResponseEntity.ok().body(voyage);
	}
	
	@PostMapping("/voyage")
	public Voyage createClient(@RequestBody Voyage voyage) {
		return repository.save(voyage);
	}
	
	@DeleteMapping("/voyage/{id}")
	public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long VoyageId)
			throws ResourceNotFoundException {
		Voyage voyage = repository.findById(VoyageId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + VoyageId));
		
		repository.delete(voyage);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@DeleteMapping("/voyage/delete")
	public ResponseEntity<String> deleteAllclient(){
		System.out.println("Delete All Articles....");
		repository.deleteAll();
		
		return new ResponseEntity<>("All Articles have been deleted!", HttpStatus.OK);
	}
	
	@PutMapping("/voyage/{id}")
	public ResponseEntity<Voyage> updateClient(@PathVariable("id") long id, @RequestBody Voyage client) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Voyage> clientInfo = repository.findById(id);
	
		if(clientInfo.isPresent()) {
			Voyage client1 = clientInfo.get();
			client1.setDateVoyage(client.getDateVoyage());
			client1.setEtat(client.getEtat());
			client1.setHeureDepart(client.getHeureDepart());
			client1.setHeureRDV(client.getHeureRDV());
			client1.setTickets(client.getTickets());
			client1.setCompagnie(client.getCompagnie());
	
			return new ResponseEntity<>(repository.save(client), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PatchMapping("voyage1/{id}")
	public ResponseEntity<Voyage> updateQlivre(@PathVariable("id") long id, @RequestBody Voyage article) {
		
		System.out.println("Update Article with ID = " + id + ".....");
		
		Optional<Voyage> articleInfo = repository.findById(id);
		
		if(articleInfo.isPresent()) {
			Voyage article1 = articleInfo.get();
			article1.setEtat(article.getEtat());
			//article1.setEtat(true);
			return new ResponseEntity<>(repository.save(article1), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		}
		
	}
	
	
	@GetMapping("/recherche")
	  public List<Voyage> parDate(@RequestParam(name="mc", defaultValue= "") String mc ) {
	    System.out.println("... Recherche par date ..."); 
	   
	    return  repository.cherche("%"+mc+"%");
	   
	  }
	
	@GetMapping("/search")
	  public List<Voyage> searchVoyage(@RequestParam(name="mc", defaultValue= "") String mc ) {
	    System.out.println("... Recherche par date ..."); 
	   
	    return  repository.searchVoyage("%"+mc+"%");
	   
	  }
}
