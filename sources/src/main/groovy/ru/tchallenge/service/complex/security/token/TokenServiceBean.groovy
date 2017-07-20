package ru.tchallenge.service.complex.security.token

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import java.time.Duration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException
import ru.tchallenge.service.complex.security.shared.PayloadService

@CompileStatic
@PackageScope
@ServiceComponent
class TokenServiceBean extends GenericServiceBean implements TokenService {

    @Autowired
    PayloadService payloadService

    @Autowired
    TokenStorage tokenStorage

    @Value('${tchallenge.security.token.deactivationInMinutes}')
    Integer deactivationInMinutes

    @Value('${tchallenge.security.token.expirationInHours}')
    Integer expirationInHours

    @Override
    TokenInfo create(String accountId) {
        def $now = now
        def $result = new TokenInfo(
                id: uuid,
                payload: payloadService.create(accountId),
                deactivationInMinutes: deactivationInMinutes,
                createdAt: $now,
                expiresAt: $now + Duration.ofHours(expirationInHours),
                lastUsedAt: $now
        )
        tokenStorage.put($result.payload, $result)
        logAsInfo('New token has been created', $result)
        $result
    }

    @Override
    TokenInfo get(String payload) {
        def $token = tokenStorage
                .get(payload)
                .orElseThrow { tokenUnknown(payload) }
        if ($token.deactivated) {
            remove(payload)
            throw tokenDeactivated($token)
        }
        if ($token.expired) {
            remove(payload)
            throw tokenExpired($token)
        }
        def $updatedToken = $token.copyWithUpdatedLastUsage()
        tokenStorage.put(payload, $updatedToken)
        logAsDebug('Token has been retrieved', payload)
        $updatedToken
    }

    @Override
    void remove(String payload) {
        tokenStorage.remove(payload)
        logAsDebug('Token has been removed', payload)
    }

    @Override
    void removeAllForAccount(String payload) {
        // TODO: implement this method
        throw new UnsupportedOperationException('Removal of all tokens for a given account is not yet implemented')
    }

    private RuntimeException tokenDeactivated(TokenInfo token) {
        SecurityViolationException.of(this, TokenViolationInfo.deactivated(token))
    }

    private RuntimeException tokenExpired(TokenInfo token) {
        SecurityViolationException.of(this, TokenViolationInfo.expired(token))
    }

    private RuntimeException tokenUnknown(String payload) {
        SecurityViolationException.of(this, TokenViolationInfo.unknown(payload))
    }
}
