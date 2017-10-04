package ru.tchallenge.service.complex.domain.sample.status

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class SampleStatusBootstrapBean extends GenericEnumeratedBootstrapBean<SampleStatus> {

    @Override
    protected Collection<SampleStatus> enumeratedEntities() {
        [
                new SampleStatus(
                        textcode: 'CREATED',
                        title: 'Создан'
                ),
                new SampleStatus(
                        textcode: 'APPROVED',
                        title: 'Подтвержден'
                ),
                new SampleStatus(
                        textcode: 'EDITED',
                        title: 'Отредактирован'
                ),
                new SampleStatus(
                        textcode: 'SUSPENDED',
                        title: 'Приостановлен'
                ),
                new SampleStatus(
                        textcode: 'DELETED',
                        title: 'Удален'
                )
        ]
    }
}
