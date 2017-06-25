package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalRepository

@CompileStatic
interface AccountRepository extends GenericOrdinalRepository<Account> {

    Account findByEmail(String email)

    Account findByLogin(String login)

    @Query("SELECT a FROM Account AS a")
    Page<Account> findPage(Pageable pageable)
}
