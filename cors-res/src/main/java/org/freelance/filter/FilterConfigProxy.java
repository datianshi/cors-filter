package org.freelance.filter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;


public class FilterConfigProxy implements InvocationHandler{
	
	private Map<String, String> properties;
	
	
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Map<String,String> getMap(){
		return properties;
	}

	@Override
	public Object invoke(Object object, Method method, Object[] args)
			throws Throwable {
		String methodName = method.getName();
		if("getInitParameterNames".equals(methodName)){
			return new Hashtable<String, String>(properties).keys();
		}
		else if ("getInitParameter".equals(methodName)){
			return properties.get(args[0]);
		}
		else
		{
			throw new IllegalArgumentException("Can not call " + object.getClass() + "." + methodName);
		}
	}
	

}
