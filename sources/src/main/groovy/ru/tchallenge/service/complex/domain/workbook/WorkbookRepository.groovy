package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository

@CompileStatic
interface WorkbookRepository extends Repository<Workbook, Long> {

    Workbook findById(Long id)

    @Query("SELECT w FROM Workbook AS w")
    Page<Workbook> findPage(Pageable pageable)

    Workbook save(Workbook entity)
}
