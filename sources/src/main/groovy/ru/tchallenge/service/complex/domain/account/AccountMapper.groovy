package ru.tchallenge.service.complex.domain.account

import groovy.transform.TypeChecked

@TypeChecked
interface AccountMapper {

    AccountInfo asInfo(Account account)
}
