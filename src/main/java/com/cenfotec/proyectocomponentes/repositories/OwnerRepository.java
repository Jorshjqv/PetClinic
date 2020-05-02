package com.cenfotec.proyectocomponentes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cenfotec.proyectocomponentes.entities.Owner;

public interface OwnerRepository extends Repository<Owner, Integer>{
	
	@Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
	@Transactional(readOnly = true)
	Collection<Owner> findByLastName(@Param("lastName") String lastName);
	
	@Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
	@Transactional(readOnly = true)
	Owner findById(@Param("id") Integer id);
	
	void save(Owner owner);
}
