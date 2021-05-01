package com.reservation.backend.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.backend.dao.PubOneRepository;
import com.reservation.backend.entities.PubOne;

@RestController
public class PubOneController {
	@Autowired
	private PubOneRepository pubOneRepository;
	
	@GetMapping(path="/imagePub/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name="id")Long id)throws Exception {
		
		PubOne p1= pubOneRepository.findById(id).get();
		String photoName=p1.getPhoto();
		File file = new File(System.getProperty("user.home")+"/reservationPhoto/images1/"+photoName);
		
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}

}
