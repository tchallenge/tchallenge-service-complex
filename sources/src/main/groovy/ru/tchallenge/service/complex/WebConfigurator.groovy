package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper

import ru.tchallenge.service.complex.reliability.correlation.CorrelationInterceptor
import ru.tchallenge.service.complex.security.SecurityInterceptor
import ru.tchallenge.service.complex.utility.serialization.InstantDeserializer
import ru.tchallenge.service.complex.utility.serialization.InstantSerializer

@CompileStatic
@Configuration
@EnableWebMvc
class WebConfigurator extends WebMvcConfigurerAdapter {

    @Autowired
    CorrelationInterceptor correlationInterceptor

    @Autowired
    SecurityInterceptor securityInterceptor

    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(correlationInterceptor)
                .addPathPatterns("/**")
        registry
                .addInterceptor(securityInterceptor)
                .addPathPatterns("/**")
    }

    @Bean
    FilterRegistrationBean corsFilter() {
        def source = new UrlBasedCorsConfigurationSource()
        def config = new CorsConfiguration()
        config.setAllowCredentials(true)
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("OPTIONS")
        config.addAllowedMethod("HEAD")
        config.addAllowedMethod("GET")
        config.addAllowedMethod("PUT")
        config.addAllowedMethod("POST")
        config.addAllowedMethod("DELETE")
        config.addAllowedMethod("PATCH")
        source.registerCorsConfiguration("/**", config)
        def bean = new FilterRegistrationBean(new CorsFilter(source))
        bean.setOrder(0)
        return bean
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
        return new MappingJackson2HttpMessageConverter(jacksonObjectMapper())
    }

    @Bean
    ObjectMapper jacksonObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .failOnEmptyBeans(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .serializers(new InstantSerializer())
                .deserializers(new InstantDeserializer())
                .build()
    }
}
