package ru.tchallenge.service.complex.reliability.correlation

import javax.servlet.http.HttpServletRequest

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericInterceptor
import ru.tchallenge.service.complex.convention.component.InterceptorComponent
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.now
import static ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals.uuid

@CompileStatic
@InterceptorComponent
class CorrelationInterceptorBean extends GenericInterceptor implements CorrelationInterceptor {

    @Autowired
    protected CorrelationContextConfigurer correlationContextConfigurer

    @Override
    protected void preHandle(HttpServletRequest request) {
        correlationContextConfigurer.setCorrelation(correlation(request))
    }

    private static CorrelationInfo correlation(HttpServletRequest request) {
        return new CorrelationInfo(
                id: uuid(),
                request: new CorrelationRequestInfo(
                        client: request.requestURI,
                        method: request.method,
                        uri: request.requestURI,
                        receivedAt: now()
                )
        )
    }
}
