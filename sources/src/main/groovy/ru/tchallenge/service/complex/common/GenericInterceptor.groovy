package ru.tchallenge.service.complex.common

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import groovy.transform.CompileStatic

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

@CompileStatic
abstract class GenericInterceptor extends HandlerInterceptorAdapter {

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return preHandle(request)
    }

    abstract boolean preHandle(HttpServletRequest request)
}
