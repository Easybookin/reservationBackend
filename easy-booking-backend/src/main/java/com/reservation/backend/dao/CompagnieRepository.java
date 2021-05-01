package com.reservation.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.reservation.backend.entities.Compagnie;


@RepositoryRestResource
@CrossOrigin("*")
public interface CompagnieRepository extends JpaRepository<Compagnie, Long> {
	
	
	

}
