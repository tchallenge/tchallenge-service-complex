package ru.tchallenge.service.complex.domain.account.verification

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('account.verification')
interface AccountVerificationRepository extends GenericEnumeratedRepository<AccountVerification> {

}
