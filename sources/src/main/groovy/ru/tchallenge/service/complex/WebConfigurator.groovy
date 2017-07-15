package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import com.fasterxml.jackson.databind.ObjectMapper

import ru.tchallenge.service.complex.reliability.correlation.CorrelationInterceptor
import ru.tchallenge.service.complex.security.SecurityInterceptor

@CompileStatic
@Configuration
@EnableWebMvc
class WebConfigurator extends WebMvcConfigurerAdapter {

    private final static String GLOBAL_PATH_PATTERN = '/**'

    @Autowired
    CorrelationInterceptor correlationInterceptor

    @Autowired
    SecurityInterceptor securityInterceptor

    @Autowired
    ObjectMapper objectMapper

    @Override
    void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(GLOBAL_PATH_PATTERN)
    }

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(correlationInterceptor)
                .addPathPatterns(GLOBAL_PATH_PATTERN)
        registry
                .addInterceptor(securityInterceptor)
                .addPathPatterns(GLOBAL_PATH_PATTERN)
    }

    @Override
    void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false)
    }

    @Override
    void configureMessageConverters(List<HttpMessageConverter<?>> list) {
        list.add(jacksonHttpMessageConverterConverter())
    }

    @Bean
    MappingJackson2HttpMessageConverter jacksonHttpMessageConverterConverter() {
        new MappingJackson2HttpMessageConverter(objectMapper)
    }
}
