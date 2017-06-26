package ru.tchallenge.service.complex.utility.routing

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import org.springframework.web.bind.annotation.RequestMethod

@CompileStatic
@Immutable
class RouteSignature {

    static RouteSignature fromRequest(HttpServletRequest request) {
        return new RouteSignature(
                method: RequestMethod.valueOf(request.method),
                uri: request.requestURI
        )
    }

    RequestMethod method
    String uri
}
