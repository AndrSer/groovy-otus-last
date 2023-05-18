package com.otus.finaletask.newsmanager.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
@ComponentScan("com.otus.finalexam.newsmanager")
class WebMvcConfiguration {

    @Bean(name = "viewResolver")
    def getViewResolver() {
        def viewResolver = new InternalResourceViewResolver()
        viewResolver.setPrefix("/WEB-INF/views")
        viewResolver.setSuffix(".jsp")
        return viewResolver
    }

}
