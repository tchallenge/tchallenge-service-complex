package ru.tchallenge.service.complex.domain.account.realm

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'account_realm')
class AccountRealm extends GenericEnumeratedEntity {

}
