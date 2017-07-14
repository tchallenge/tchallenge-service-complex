package ru.tchallenge.service.complex.reliability.correlation

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.uuid

@CompileStatic
@Immutable
final class CorrelationInfo {

    static CorrelationInfo of(HttpServletRequest request) {
        return new CorrelationInfo(
                id: uuid(),
                request: CorrelationRequestInfo.of(request)
        )
    }

    String id
    CorrelationRequestInfo request
}
