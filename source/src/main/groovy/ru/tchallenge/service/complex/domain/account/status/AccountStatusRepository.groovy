package ru.tchallenge.service.complex.domain.account.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('account.status')
interface AccountStatusRepository extends GenericEnumeratedRepository<AccountStatus> {

}
