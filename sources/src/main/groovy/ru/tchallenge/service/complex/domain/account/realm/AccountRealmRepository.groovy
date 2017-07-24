package ru.tchallenge.service.complex.domain.account.realm

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('account.realm')
interface AccountRealmRepository extends GenericEnumeratedRepository<AccountRealm> {

}
