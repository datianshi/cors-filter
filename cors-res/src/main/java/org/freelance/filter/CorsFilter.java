package org.freelance.filter;

import java.lang.reflect.Proxy;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thetransactioncompany.cors.CORSFilter;

@Component
public class CorsFilter extends CORSFilter implements InitializingBean {

	@Autowired
	FilterConfigProxy proxy;

	@Override
	public void afterPropertiesSet() throws ServletException {
		FilterConfig config = (FilterConfig) Proxy.newProxyInstance(FilterConfig.class.getClassLoader(), new Class<?>[] {FilterConfig.class}, proxy);
		super.init(config);
	}
	
	public void refresh(final Map<String, String> map) throws ServletException{
		proxy.setProperties(map);
		FilterConfig config = (FilterConfig) Proxy.newProxyInstance(FilterConfig.class.getClassLoader(), new Class<?>[] {FilterConfig.class}, proxy);
		super.init(config);
	}
	
	public Map<String,String> getConfig(){
		return proxy.getMap();
	}	

}
