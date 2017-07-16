package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import java.time.Duration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.reliability.exception.SecurityViolationException

@CompileStatic
@PackageScope
@ServiceComponent
class RescueServiceBean extends GenericService implements RescueService {

    @Autowired
    RescuePayloadService rescuePayloadService

    @Autowired
    RescueStorage rescueStorage

    @Value('${tchallenge.security.rescue.expirationInMinutes}')
    Integer expirationInMinutes

    @Override
    RescueInfo create(String accountId) {
        def $now = now
        def $result = new RescueInfo(
                id: uuid,
                payload: rescuePayloadService.create(accountId),
                createdAt: $now,
                expiresAt: $now + Duration.ofMinutes(expirationInMinutes)
        )
        rescueStorage.put($result.payload, $result)
        logAsInfo('New rescue has been created', $result)
        $result
    }

    @Override
    RescueInfo getAndRemove(String payload) {
        def $result = rescueStorage
                .get(payload)
                .orElseThrow { rescueUnknown(payload) }
        if ($result.expired) {
            remove(payload)
            throw rescueExpired($result)
        }
        logAsDebug('Rescue has been retrieved', payload)
        $result
    }

    @Override
    void remove(String payload) {
        rescueStorage.remove(payload)
        logAsDebug('Rescue has been removed', payload)
    }

    @Override
    void removeAllForAccount(String payload) {
        // TODO: implement this method
        throw new UnsupportedOperationException('Removal of all rescues for a given account is not yet implemented')
    }

    private SecurityViolationException rescueExpired(RescueInfo rescue) {
        SecurityViolationException.of(this, RescueViolationInfo.expired(rescue))
    }

    private SecurityViolationException rescueUnknown(String payload) {
        SecurityViolationException.of(this, RescueViolationInfo.unknown(payload))
    }
}
