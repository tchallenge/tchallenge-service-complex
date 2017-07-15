package ru.tchallenge.service.complex.utility.routing

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import javax.servlet.http.HttpServletRequest

import org.springframework.web.bind.annotation.RequestMethod

@CompileStatic
@Immutable
class RouteSignature {

    static RouteSignature fromRequest(HttpServletRequest request) {
        new RouteSignature(
                method: RequestMethod.valueOf(request.method),
                uri: request.requestURI
        )
    }

    RequestMethod method
    boolean pattern = false
    String uri

    boolean matches(RouteSignature another) {
        this == another || (pattern && method == another.method && another.uri.matches(uri))
    }
}
