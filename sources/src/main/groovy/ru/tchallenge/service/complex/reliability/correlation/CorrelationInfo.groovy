package ru.tchallenge.service.complex.reliability.correlation

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import javax.servlet.http.HttpServletRequest

import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals

@CompileStatic
@Immutable
final class CorrelationInfo {

    static CorrelationInfo of(HttpServletRequest request) {
        return new CorrelationInfo(
                id: Foundamentals.uuid,
                request: CorrelationRequestInfo.of(request)
        )
    }

    String id
    CorrelationRequestInfo request
}
