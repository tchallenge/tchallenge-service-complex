package ru.tchallenge.service.complex.domain.workbook

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.behavior.value.GenericInfoValue
import ru.tchallenge.service.complex.convention.value.Value

@CompileStatic
@Value
class WorkbookInfo extends GenericInfoValue {

    String id
}
