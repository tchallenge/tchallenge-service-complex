package ru.tchallenge.service.complex.reliability.correlation

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
final class CorrelationRequestClientInfo {

    static CorrelationRequestClientInfo of(HttpServletRequest request) {
        return new CorrelationRequestClientInfo(
                address: request.remoteAddr,
                hostname: request.remoteHost,
                port: request.remotePort
        )
    }

    String address
    String hostname
    Integer port
}
