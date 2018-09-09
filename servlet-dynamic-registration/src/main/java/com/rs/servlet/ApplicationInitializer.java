package com.rs.servlet;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		ServletRegistration.Dynamic registration = ctx.addServlet("dynaServlet", DynamicServlet.class);
		registration.addMapping("/dyna");
		registration.setLoadOnStartup(1);
	}
}
