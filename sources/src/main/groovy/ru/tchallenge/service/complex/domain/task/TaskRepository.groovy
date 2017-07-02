package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalRepository

@CompileStatic
interface TaskRepository extends GenericOrdinalRepository<Task> {

    @Query("""SELECT t FROM Task AS t
              
                WHERE   (
                            :textPattern IS NULL
                        OR
                            upper(t.title) LIKE :textPattern
                        OR
                            upper(t.introduction) LIKE :textPattern
                        OR
                            upper(t.question) LIKE :textPattern
                        )

                    AND (
                            :statusTextcodes IS NULL
                        OR
                            t.status.textcode IN :statusTextcodes
                        )""")
    Page<Task> findPage(@Param("textPattern") String textPattern,
                        @Param("statusTextcodes") Collection<String> statusTextcodes,
                        Pageable pageable)
}
