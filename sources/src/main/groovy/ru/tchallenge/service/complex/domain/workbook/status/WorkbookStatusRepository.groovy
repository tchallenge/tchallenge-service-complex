package ru.tchallenge.service.complex.domain.workbook.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('workbook.status')
interface WorkbookStatusRepository extends GenericEnumeratedRepository<WorkbookStatus> {

}
