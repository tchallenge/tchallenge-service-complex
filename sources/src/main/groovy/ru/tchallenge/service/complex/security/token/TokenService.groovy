package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic

import java.time.Duration

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationCategory

@CompileStatic
@ServiceComponent
class TokenService extends GenericService {

    @Autowired
    protected TokenPayloadService payloadService

    @Autowired
    protected TokenStorage tokenStorage

    private static final Integer deactivationInMinutes = 30
    private static final Integer expirationInHours = 12

    TokenInfo create(String accountId) {
        def now = now
        def token = new TokenInfo(
                id: uuid,
                payload: payloadService.fromAccountId(accountId),
                deactivationInMinutes: deactivationInMinutes,
                createdAt: now,
                expiresAt: now + Duration.ofHours(expirationInHours),
                lastUsedAt: now
        )
        tokenStorage.put(token.payload, token)
        return token
    }

    TokenInfo get(String payload) {
        def tokenOptional = tokenStorage.get(payload)
        if (!tokenOptional.isPresent()) {
            throw tokenUnknown(payload)
        }
        def token = tokenOptional.get()
        if (token.deactivated) {
            remove(payload)
            throw tokenDeactivated(token)
        }
        if (token.expired) {
            remove(payload)
            throw tokenExpired(token)
        }
        def updatedToken = token.copyWithUpdatedLastUsage()
        tokenStorage.put(payload, updatedToken)
        return updatedToken
    }

    void remove(String payload) {
        tokenStorage.remove(payload)
    }

    void removeForAccount(String payload) {
        // TODO: implement removal of all Account's tokens by given payload
        throw new UnsupportedOperationException()
    }

    private static RuntimeException tokenDeactivated(TokenInfo token) {
        return new SecurityViolationException(
                new TokenViolationInfo(
                        base: new BaseViolationInfo(
                                category: ViolationCategory.SECURITY,
                                description: "token has been deactivated due to absence of activity",
                                textcode: "X.SECURITY.TOKEN.DEACTIVATED"
                        ),
                        token: token
                )
        )
    }

    private static RuntimeException tokenExpired(TokenInfo token) {
        return new SecurityViolationException(
                new TokenViolationInfo(
                        base: new BaseViolationInfo(
                                category: ViolationCategory.SECURITY,
                                description: "token has been expired",
                                textcode: "X.SECURITY.TOKEN.EXPIRED"
                        ),
                        token: token
                )
        )
    }

    private static RuntimeException tokenUnknown(String payload) {
        return new SecurityViolationException(
                new TokenViolationInfo(
                        base: new BaseViolationInfo(
                                category: ViolationCategory.SECURITY,
                                description: "token has not been recognized",
                                textcode: "X.SECURITY.TOKEN.UNKNOWN"
                        ),
                        payload: payload
                )
        )
    }
}
