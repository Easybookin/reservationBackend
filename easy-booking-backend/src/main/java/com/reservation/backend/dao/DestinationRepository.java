package com.reservation.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.reservation.backend.entities.Destination;



@RepositoryRestResource
@CrossOrigin("*")
public interface DestinationRepository extends JpaRepository<Destination, Long> {
	
	//@Query("SELECT d FROM Destination as d WHERE d.paysResidence =:a AND d.nature =:b")
	//public List<Destination> findByDest(@Param("a") PaysResidence a, @Param("b") Nature b);
	
	
	//@Query("SELECT d FROM Destination as d WHERE d.paysResidence =:a AND d.nature =:b AND d.id =:id")
	//public List<Destination> findByDesCompagnie(@Param("a") PaysResidence a, @Param("b") Nature b, @Param("id") long id);
	
}
