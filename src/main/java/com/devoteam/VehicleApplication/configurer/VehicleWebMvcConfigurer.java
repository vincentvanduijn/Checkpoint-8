package com.devoteam.VehicleApplication.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class VehicleWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers (List<HandlerMethodArgumentResolver> resolver) {
        PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver();
        pageableHandlerMethodArgumentResolver.setFallbackPageable(PageRequest.of(0, 10));
        resolver.add(pageableHandlerMethodArgumentResolver);
    }

    // Does this even work if you have to fill in the Pageable criteria???
}
