package ru.tchallenge.service.complex.reliability.exception

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import javax.servlet.http.HttpServletRequest

import org.springframework.web.HttpRequestMethodNotSupportedException

import com.fasterxml.jackson.annotation.JsonIgnore

@CompileStatic
@Immutable
final class NotSupportedMethodExceptionInfo implements ExceptionInfo {

    static NotSupportedMethodExceptionInfo of(HttpServletRequest request,
                                              HttpRequestMethodNotSupportedException exception) {
        new NotSupportedMethodExceptionInfo(
                base: BaseExceptionInfo.of(ExceptionCategory.UNSUPPORTED),
                method: exception.method,
                uri: request.requestURI
        )
    }

    @Delegate
    @JsonIgnore
    BaseExceptionInfo base

    String method
    String uri
}
