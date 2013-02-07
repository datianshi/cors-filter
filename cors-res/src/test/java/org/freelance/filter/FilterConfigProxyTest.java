package org.freelance.filter;

import java.lang.reflect.Proxy;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class FilterConfigProxyTest {
	
	Map<String, String> props;
	
	@Before
	public void setup(){
		props = new HashMap<String,String>();
		props.put("key1", "test1");
		props.put("key2", "test2");
	}
	
	@Test
	public void shouldReturnCorrectEnumeration() {
		
		FilterConfig config = getProxy(props);
		Enumeration e = config.getInitParameterNames();
		while(e.hasMoreElements()){
			Object nextElement = e.nextElement();
			Assert.assertEquals(props.get(nextElement), config.getInitParameter((String) nextElement));
		}
	}
	
	
	private FilterConfig getProxy(Map<String, String> properties){
		FilterConfigProxy config = new FilterConfigProxy();
		config.setProperties(properties);
		return (FilterConfig) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[] {FilterConfig.class}, config);
	}

}
