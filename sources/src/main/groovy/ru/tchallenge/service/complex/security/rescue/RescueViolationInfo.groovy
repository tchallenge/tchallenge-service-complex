package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic
import groovy.transform.Immutable

import com.fasterxml.jackson.annotation.JsonIgnore

import ru.tchallenge.service.complex.reliability.violation.BaseViolationInfo
import ru.tchallenge.service.complex.reliability.violation.SecurityViolationInfo
import ru.tchallenge.service.complex.reliability.violation.ViolationInfo

@CompileStatic
@Immutable
final class RescueViolationInfo implements ViolationInfo {

    static RescueViolationInfo expired(RescueInfo rescue) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Rescue has been expired',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.RESCUE.EXPIRED"
                )
        )
        new RescueViolationInfo(
                base: base,
                payload: rescue.payload,
                rescue: rescue
        )
    }

    static RescueViolationInfo unknown(String payload) {
        def base = new SecurityViolationInfo(
                base: new BaseViolationInfo(
                        category: SecurityViolationInfo.CATEGORY,
                        description: 'Rescue payload has not been recognized',
                        textcode: "${SecurityViolationInfo.TEXTCODE_PREFIX}.RESCUE.UNKNOWN"
                )
        )
        new RescueViolationInfo(
                base: base,
                payload: payload
        )
    }
    @Delegate
    @JsonIgnore
    SecurityViolationInfo base

    String payload
    RescueInfo rescue
}
