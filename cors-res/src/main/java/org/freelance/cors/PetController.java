package org.freelance.cors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.freelance.filter.CorsFilter;
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
	CorsFilter filter;
	
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
	
	@RequestMapping(value="/config",method=RequestMethod.GET)
	public ResponseEntity<Map<String,String>> getConfig(){
		 return new ResponseEntity<Map<String,String>>(filter.getConfig(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/config",method=RequestMethod.POST)
	public ResponseEntity<Map<String, String>> postConfig(@RequestBody Map<String,String> map) throws ServletException{
		 filter.refresh(map);
		 Map<String, String> hashMap = new HashMap<String, String>();
		 hashMap.put("success", "success update cors filter config");
		 return new ResponseEntity<Map<String, String>>(hashMap, HttpStatus.OK);
	}	
}
