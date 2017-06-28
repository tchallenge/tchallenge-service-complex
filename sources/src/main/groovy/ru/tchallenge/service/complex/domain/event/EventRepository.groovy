package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalRepository

@CompileStatic
interface EventRepository extends GenericOrdinalRepository<Event> {

    Event findByTextcode(String textcode)

    @Query("""SELECT e FROM Event AS e
              
                WHERE   (
                            :textPattern IS NULL
                        OR
                            upper(e.title) LIKE :textPattern
                        OR
                            upper(e.description) LIKE :textPattern
                        )

                    AND (
                            :statusTextcodes IS NULL
                        OR
                            e.status.textcode IN :statusTextcodes
                        )""")
    Page<Event> findPage(@Param("textPattern") String textPattern,
                         @Param("statusTextcodes") Collection<String> statusTextcodes,
                         Pageable pageable)
}
