package ru.tchallenge.service.complex.common

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import groovy.transform.CompileStatic

import org.springframework.web.servlet.AsyncHandlerInterceptor
import org.springframework.web.servlet.ModelAndView

import ru.tchallenge.service.complex.utility.routing.RouteSignature

@CompileStatic
abstract class GenericInterceptor extends GenericComponent implements AsyncHandlerInterceptor {

    @Override
    boolean preHandle(HttpServletRequest request,
                      HttpServletResponse response,
                      Object handler) throws Exception {
        if (shouldBypass(request)) {
            return true
        }
        preHandle(request)
        return true
    }

    @Override
    void postHandle(HttpServletRequest request,
                    HttpServletResponse response,
                    Object handler,
                    ModelAndView modelAndView) throws Exception {

    }

    @Override
    void afterCompletion(HttpServletRequest request,
                         HttpServletResponse response,
                         Object handler,
                         Exception exception) throws Exception {

    }

    @Override
    void afterConcurrentHandlingStarted(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Object handler) throws Exception {

    }

    protected void preHandle(HttpServletRequest request) {

    }

    protected boolean shouldBypass(HttpServletRequest request) {
        def signature = RouteSignature.fromRequest(request)
        for (def exclusion : exclusions) {
            if (exclusion.matches(signature)) {
                return true
            }
        }
        return false
    }

    protected Collection<RouteSignature> getExclusions() {
        return []
    }
}
