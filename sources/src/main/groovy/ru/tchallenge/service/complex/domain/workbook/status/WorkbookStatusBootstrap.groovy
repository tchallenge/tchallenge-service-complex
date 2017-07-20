package ru.tchallenge.service.complex.domain.workbook.status

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class WorkbookStatusBootstrap extends GenericEnumeratedBootstrapBean<WorkbookStatus> {

    @Override
    protected Collection<WorkbookStatus> enumeratedEntities() {
        return [
                new WorkbookStatus(
                        textcode: "CREATED",
                        title: "Создана"
                ),
                new WorkbookStatus(
                        textcode: "SUBMITTED",
                        title: "Отправлена на проверку"
                ),
                new WorkbookStatus(
                        textcode: "ASSESSED",
                        title: "Проверена"
                ),
                new WorkbookStatus(
                        textcode: "DISCARDED",
                        title: "Отменена"
                ),
                new WorkbookStatus(
                        textcode: "DELETED",
                        title: "Удалена"
                )
        ]
    }
}
