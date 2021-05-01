package com.reservation.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.reservation.backend.entities.Voyage;


@RepositoryRestResource
@CrossOrigin("*")
public interface VoyageRepository extends JpaRepository<Voyage, Long>{
	
	@Query("select a from Voyage a where a.etat=false")
	public List<Voyage> cherche(@Param("x") String mc);
	
	@Query("select a from Voyage a where a.dateVoyage like :x and a.etat=false")
	public List<Voyage> searchVoyage(@Param("x") String mc);
	
}
