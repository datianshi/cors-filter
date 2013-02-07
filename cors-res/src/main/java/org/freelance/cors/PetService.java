package org.freelance.cors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class PetService {
	
	Map<Long, Pet> pets = new HashMap<Long, Pet>();
	
	
	private AtomicLong key = new AtomicLong(0);
	
	public Pet put(Long id, Pet p){
		pets.put(id, p);
		return p;
	}
	
	public Pet create(Pet p){
		long id = key.incrementAndGet();
		p.setId(new Long(id));
		pets.put(new Long(id), p);
		return p;
	}	
	
	public Pet delete(Long id){
		return pets.remove(id);
	}
	
	public Pet get(Long id){
		return pets.get(id);
	}

	public List<Pet> getAll() {
		return new ArrayList<Pet>(pets.values());
	}
	
	public void refreshApplicationContext(){
		
	}
	
}
