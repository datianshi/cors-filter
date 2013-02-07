package org.freelance.cors;

import org.freelance.cors.Pet;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

@Ignore
public class RestTest {
	@Test
	public void retrieve(){
		RestTemplate template = new RestTemplate();
		Pet pet = template.getForObject("http://localhost:8080/cors/pet/100", Pet.class);
	}
	
	
	@Test
	public void put(){
		RestTemplate template = new RestTemplate();
		Pet update = new Pet();
		update.setName("Mary Chrismas");
		template.put("http://localhost:8080/cors/pet/1", update);
	}	
}
