package ru.tchallenge.service.complex.domain.account

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalRepository

@CompileStatic
interface AccountRepository extends GenericOrdinalRepository<Account> {

    Account findByEmail(String email)

    Account findByLogin(String login)

    @Query('''SELECT a FROM Account AS a
                
                JOIN a.person AS p
              
                WHERE   (
                            :emailPattern IS NULL
                        OR
                            upper(a.email) LIKE :emailPattern
                        )

                    AND (
                            :loginPattern IS NULL
                        OR
                            upper(a.login) LIKE :loginPattern
                        )

                    AND (
                            :personNamePattern IS NULL
                        OR
                            upper(p.firstname) LIKE :personNamePattern
                        OR
                            upper(p.lastname) LIKE :personNamePattern
                        OR
                            upper(p.quickname) LIKE :personNamePattern
                        )

                    AND (
                            :realmTextcodes IS NULL
                        OR
                            a.realm.textcode IN :realmTextcodes
                        )

                    AND (
                            :statusTextcodes IS NULL
                        OR
                            a.status.textcode IN :statusTextcodes
                        )''')
    Page<Account> findPage(@Param('emailPattern') String emailPattern,
                           @Param('loginPattern') String loginPattern,
                           @Param('personNamePattern') String personNamePattern,
                           @Param('realmTextcodes') Collection<String> realmTextcodes,
                           @Param('statusTextcodes') Collection<String> statusTextcodes,
                           Pageable pageable)
}
