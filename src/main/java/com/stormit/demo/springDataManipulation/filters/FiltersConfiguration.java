package com.stormit.demo.springDataManipulation.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfiguration {

    @Bean
    public FilterRegistrationBean<DebugLoggingFilter> loggingFilter(){
        FilterRegistrationBean<DebugLoggingFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new DebugLoggingFilter());
        registrationBean.addUrlPatterns("/notes/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }
}

