package org.freelance.cors;

import java.util.HashMap;
import java.util.Map;

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
	
	@Test
	public void should_retrieve_the_filter_config(){
		RestTemplate template = new RestTemplate();
		Map<String,String> map = template.getForObject("http://localhost:8080/cors-res/pet/config", Map.class);
		System.out.println(map.size());
		System.out.println(map.get("cors.allowSubdomains"));
	}
	
//	<init-param>
//	<param-name>cors.supportedHeaders</param-name>
//	<param-value>Accept,Origin,Content-Type</param-value>
//</init-param>
//<init-param>
//	<param-name>cors.supportedMethods</param-name>
//	<param-value>GET,POST,HEAD,PUT,DELETE</param-value>
//</init-param>
//<init-param>
//	<param-name>cors.allowOrigin</param-name>
//	<param-value>http://techdora.cloudfoundry.me</param-value>
//</init-param>
//<init-param>
//	<param-name>cors.allowSubdomains</param-name>
//	<param-value>true</param-value>
//</init-param>
	
	
	@Test
	public void postConfig(){
		RestTemplate template = new RestTemplate();
		Map<String,String> map = new HashMap<String,String>();
		map.put("cors.supportedHeaders", "Accept,Origin,Content-Type");
		map.put("cors.supportedMethods", "GET,POST,HEAD,PUT,DELETE");
		map.put("cors.allowOrigin", "http://techdora.cloudfoundry.me");
		map.put("cors.allowSubdomains", "true");
		
		String message = template.postForObject("http://cors-res.techdora.cloudfoundry.me/pet/config", map, String.class);
		System.out.println(message);
	}	
}
