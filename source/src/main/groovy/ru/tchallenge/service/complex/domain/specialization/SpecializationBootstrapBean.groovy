package ru.tchallenge.service.complex.domain.specialization

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class SpecializationBootstrapBean extends GenericEnumeratedBootstrapBean<Specialization> {

    @Override
    protected Collection<Specialization> enumeratedEntities() {
        [
                new Specialization(
                        textcode: 'JAVABACKEND',
                        title: 'Разработчик серверных компонентов (Java)'
                ),
                new Specialization(
                        textcode: 'JAVAFRONTEND',
                        title: 'Разработчик UI компонентов (Java)'
                ),
                new Specialization(
                        textcode: 'JAVAFULLSTACK',
                        title: 'Разработчик (Java)'
                ),
                new Specialization(
                        textcode: 'TESTER',
                        title: 'Инженер по тестированию'
                )
        ]
    }
}
