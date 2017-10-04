package ru.tchallenge.service.complex.reliability.correlation

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
final class CorrelationRequestInfo {

    static CorrelationRequestInfo of(HttpServletRequest request) {
        return new CorrelationRequestInfo(
                client: CorrelationRequestClientInfo.of(request),
                method: request.method,
                uri: request.requestURI
        )
    }

    CorrelationRequestClientInfo client
    String method
    String uri
}
