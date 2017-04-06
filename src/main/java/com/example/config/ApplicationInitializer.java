package com.example.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext applicationConfig = new AnnotationConfigWebApplicationContext();
		
		applicationConfig.register(AppConfig.class);
	
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationConfig);
		
		ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher", dispatcherServlet);
		
		servletRegistration.setLoadOnStartup(1);
		servletRegistration.addMapping("/");
		
		//wymuszenie kodowania dla danych z requestow
		CharacterEncodingFilter charEnc = new CharacterEncodingFilter();
		charEnc.setEncoding("UTF-8");
		charEnc.setForceEncoding(true);
		servletContext.addFilter("charEnc", charEnc).addMappingForUrlPatterns(null, true, "/*");
		
		//deklaracja ze wszystkie beany sa zwiazane ze spring security
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
				
				
		servletContext.addFilter("springSecurityFilterChain", delegatingFilterProxy).addMappingForUrlPatterns(null, true, "/*");
		
	}

}
