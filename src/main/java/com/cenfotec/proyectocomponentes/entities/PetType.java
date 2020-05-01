package com.cenfotec.proyectocomponentes.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cenfotec.proyectocomponentes.entities.*;

/**
 * @author Juergen Hoeller Can be Cat, Dog, Hamster...
 */
@Entity
@Table(name = "types")
public class PetType extends NamedEntity {

}