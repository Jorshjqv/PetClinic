package com.cenfotec.proyectocomponentes.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity{
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType type;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Owner owner;
	
	@Transient
	private Set<Visit> visits = new LinkedHashSet<>();
	
	
	protected Set<Visit> getVisitsInt(){
		if(this.visits == null) {
			this.visits = new HashSet<>();			
		}
		
		return this.visits;
	}
	
	protected void setVisitsInt(Collection<Visit> visits) {
		this.visits = new LinkedHashSet<>(visits);
	}
	
	public List<Visit> getVisits(){
		List<Visit> sorted = new ArrayList<>(getVisitsInt());
		PropertyComparator.sort(sorted, new MutableSortDefinition("date", false, false));
		
		return Collections.unmodifiableList(sorted);
	}
	
	public void addVisit(Visit visit) {
		getVisitsInt().add(visit);
		visit.setPetId(this.getId());
	}
}
