package ru.tchallenge.service.complex.security.token

import java.time.Duration

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

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
        def now = now()
        def token = new TokenInfo(
                id: uuid(),
                accountId: accountId,
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
            throw new RuntimeException("token's payload has not been recognized")
        }
        def token = tokenOptional.get()
        if (token.deactivated) {
            remove(payload)
            throw new RuntimeException("token has been deactivated due to absence of activity")
        }
        if (token.expired) {
            remove(payload)
            throw new RuntimeException("token has been expired")
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
}
