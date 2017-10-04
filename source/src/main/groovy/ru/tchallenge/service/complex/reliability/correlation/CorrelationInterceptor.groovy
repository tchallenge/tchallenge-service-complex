package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic

import org.springframework.web.servlet.AsyncHandlerInterceptor

@CompileStatic
interface CorrelationInterceptor extends AsyncHandlerInterceptor {

}
