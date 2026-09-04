package com.manualspring;

import com.manualspring.config.AppConfig;
import com.manualspring.config.WebAppConfig;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

import java.util.EnumSet;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class MainSpringManualApplication implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class, AppConfig.class);

        DispatcherServlet ds = new DispatcherServlet(context);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcherServlet", ds);

        DelegatingFilterProxy dfp = new DelegatingFilterProxy("springSecurityFilterChain");
        FilterRegistration.Dynamic delegatingFilterProxyRegistration = servletContext.addFilter("springSecurityFilterChain", dfp);

        delegatingFilterProxyRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC), false, "/*");

        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
