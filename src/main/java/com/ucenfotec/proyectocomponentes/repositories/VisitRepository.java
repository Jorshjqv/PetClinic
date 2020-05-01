package com.ucenfotec.proyectocomponentes.repositories;

import com.ucenfotec.proyectocomponentes.entities.*;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
//import org.springframework.samples.petclinic.model.BaseEntity;
//import org.springframework.samples.petclinic.visit.Visit;

public interface VisitRepository extends Repository<Visit, Integer> {


	void save(Visit visit) throws DataAccessException;

	List<Visit> findByPetId(Integer petId);

}