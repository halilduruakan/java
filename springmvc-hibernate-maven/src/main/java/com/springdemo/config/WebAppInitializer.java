package com.springdemo.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.springdemo.config");
		
		container.addFilter("springSecurityFilterChain", 
							new DelegatingFilterProxy("springSecurityFilterChain"))
							.addMappingForUrlPatterns(null, false, "/*");

		container.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(context));
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		

	}

}
