package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository

@CompileStatic
interface WorkbookRepository extends Repository<WorkbookEntity, Long> {

    WorkbookEntity findById(Long id)

    @Query("SELECT w FROM WorkbookEntity AS w")
    Page<WorkbookEntity> findPage(Pageable pageable)

    WorkbookEntity save(WorkbookEntity entity)
}
