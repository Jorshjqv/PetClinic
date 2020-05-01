package com.cenfotec.proyectocomponentes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.proyectocomponentes.entities.Pet;

public interface PetRepository extends Repository<Pet, Integer>{
	
	@Query("SELECT ptype FROM petType ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findByPetTypes();
	
	@Transactional(readOnly = true)
	Pet findById(Integer id);
	
	void save(Pet pet);
}