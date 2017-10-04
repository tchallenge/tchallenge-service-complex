package ru.tchallenge.service.complex.domain.account.status

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'account_status')
class AccountStatus extends GenericEnumeratedEntity {

}
