package com.otus.finaletask.newsmanager.configuration

import jakarta.servlet.Registration
import jakarta.servlet.ServletContext
import jakarta.servlet.ServletException
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class WebAppInitializer implements WebApplicationInitializer {

    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        def applicationContext = new AnnotationConfigWebApplicationContext()
        applicationContext.register(WebMvcConfiguration.class)

        Registration.Dynamic dispatcher = servletContext
                .addServlet("SpringDispatcher",
                        new DispatcherServlet(applicationContext))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")
    }
}
