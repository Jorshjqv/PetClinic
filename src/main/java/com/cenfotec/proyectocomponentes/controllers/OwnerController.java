package com.cenfotec.proyectocomponentes.controllers;

import java.security.acl.Owner;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.PostMapping;

import com.cenfotec.proyectocomponentes.entities.*;

import com.cenfotec.proyectocomponentes.entities.*;
import com.cenfotec.proyectocomponentes.repositories.OwnerRepository;
import com.cenfotec.proyectocomponentes.repositories.VisitRepository;

import lombok.Data;

@Controller
@Data
public class OwnerController {
	
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateForm";
	
	private final OwnerRepository owners;
	
    private VisitRepository visits;
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
    	dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping("/owners/new")
    public String initCreationForm(Map<String,Object> model) {
    	Owner owner = new Owner();
    	model.put("owner", owner);
    	  
    	return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
    	if(result .hasErrors()) {
    		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    	}
    	else {
    		this.owners.save(owner);
    		return "redirect:/owners/" + owner.getId();
    	}
    }
    
    @GetMapping("/owners/find")
    public String initFindForm(Map<String, Object> model) {
    	model.put("owner", new Owner())
    	return "owner/findOwners";
    }
    
    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {
    	if(owner.getLastName() == null) {
    		owner.setLaCollection<E>;
    	}
    	
    	Collection<Owner> results = this.owners.findByLastName(owner.getLastName());
    	if(results.isEmpty()) {
    		result.rejectValue("lastName", "notFound", "not found");
    		return "owners/findOwners";
    	}
    	else if(results.size() == 1) {
    		owner = results.iterator().next();
    		return "redirect:/owners/" + owner.getId();
    	}
    	else {
    		model.put("selections", results);
    		return "owners/ownerList";
    	}
    }
    
    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
    	Owner owner = this.owners.findById(ownerId);
    	model.addAttribute(owner);
    	
    	return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
    	if(result.hasErrors()) {
    		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    	}
    	else {
    		owner.setId(ownerId);
    		this.owners.save(owner);
    		return "redirect:/owners/{ownerId}";
    	}
    }
    
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
    	ModelAndView  mav =  new ModelAndView("owners/ownerDetails");
    	Owner owner = this.owners.findById(ownerId);
    	for(Pet pet: owner.getPets()) {
    		pet.setVisits(visits.findByPetId(pet.getId()));
    	}
    	
    	mav.addObject(owner);
    	return mav;
    }
    
    
}
