package com.cenfotec.proyectocomponentes.entities;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
;

@MappedSuperclass
@Data
public class Person extends NamedEntity{
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
}
