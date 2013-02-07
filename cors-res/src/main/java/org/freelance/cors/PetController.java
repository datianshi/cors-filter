package org.freelance.cors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	PetService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Pet> post(@RequestBody Pet pet){
		 Pet p = service.create(pet);
		 return new ResponseEntity<Pet>(p, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Pet>> getAll(){
		 return new ResponseEntity<List<Pet>>(service.getAll(), HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Pet> put(@PathVariable("id") final Long id, @RequestBody Pet pet){
		 Pet p = service.put(id, pet);
		 return new ResponseEntity<Pet>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Pet> delete(@PathVariable("id") final Long id){
		 Pet p = service.delete(id);
		 return new ResponseEntity<Pet>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pet> get(@PathVariable Long id){
		 Pet p = service.get(id);
		 return new ResponseEntity<Pet>(p, HttpStatus.OK);
	}	
}
