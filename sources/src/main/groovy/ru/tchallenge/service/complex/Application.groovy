package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer

@CompileStatic
@SpringBootApplication
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration])
class Application extends SpringBootServletInitializer {

    static void main(String[] arguments) {
        SpringApplication.run(Application, arguments)
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application)
    }
}
