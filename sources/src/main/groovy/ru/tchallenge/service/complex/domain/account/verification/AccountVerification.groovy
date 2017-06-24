package ru.tchallenge.service.complex.domain.account.verification

import javax.persistence.Entity
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = "account_verification")
class AccountVerification extends GenericEnumeratedEntity {

}
