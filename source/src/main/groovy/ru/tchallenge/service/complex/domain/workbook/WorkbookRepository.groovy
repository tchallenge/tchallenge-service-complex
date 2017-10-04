package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalRepository

@CompileStatic
interface WorkbookRepository extends GenericOrdinalRepository<Workbook> {

    @Query("""SELECT w FROM Workbook AS w
              
                WHERE   (
                            :eventId IS NULL
                        OR
                            w.event.id IN :eventIds
                        )
              
                    AND (
                            :ownerId IS NULL
                        OR
                            w.owner.id IN :ownerIds
                        )

                    AND (
                            :statusTextcodes IS NULL
                        OR
                            w.status.textcode IN :statusTextcodes
                        )""")
    Page<Workbook> findPage(Collection<Long> eventIds,
                            Collection<Long> ownerIds,
                            Collection<String> statusTextcodes,
                            Pageable pageable)
}
