package com.cenfotec.proyectocomponentes.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import lombok.Data;

@Data
@Entity
@Table(name = "owners")
public class Owner extends Person{
	
	@NotEmpty
	private String address;
	@NotEmpty
	private String city;
	
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets;
	
	protected Set<Pet> getPetsInt(){
		if(this.pets == null) {
			this.pets = new HashSet<>();
		}
		
		return this.pets;
	}
	
	protected void setPetsInt(Set<Pet> pets) {
		this.pets = pets;
	}
	
	public List<Pet> getPets(){
		List<Pet> sorted = new ArrayList<>(getPetsInt());
		PropertyComparator.sort(sorted, new MutableSortDefinition("name", true, true));
		
		return Collections.unmodifiableList(sorted);
	}
	
	public void addPet(Pet pet) {
		if(pet.isNew()) {
			getPetsInt().add(pet);
		}
		
		pet.setOwner(this);
	}
	
	public Pet getPet(String name) {
		return getPet(name, false);
	}
	
	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet: getPetsInt()) {
			if(!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if(compName.equals(name)) {
					return pet;
				}
			}
		}
		
		return null;
	}
	
	
	
	
}
