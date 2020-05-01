package com.cenfotec.proyectocomponentes.repositories;

import com.cenfotec.proyectocomponentes.entities.*;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


public interface VisitRepository extends Repository<Visit, Integer> {


	void save(Visit visit) throws DataAccessException;

	List<Visit> findByPetId(Integer petId);

}