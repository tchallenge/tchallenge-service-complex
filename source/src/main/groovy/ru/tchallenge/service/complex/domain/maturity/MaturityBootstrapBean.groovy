package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@PackageScope
@BootstrapComponent
class MaturityBootstrapBean extends GenericEnumeratedBootstrapBean<Maturity> {

    @Override
    protected Collection<Maturity> enumeratedEntities() {
        [
                new Maturity(
                        textcode: 'STUDENT',
                        title: 'Студенты, начало карьеры'
                ),
                new Maturity(
                        textcode: 'JUNIOR',
                        title: 'Младшие специалисты'
                ),
                new Maturity(
                        textcode: 'INTERMEDIATE',
                        title: 'Специалисты с практическим опытом'
                ),
                new Maturity(
                        textcode: 'SENIOR',
                        title: 'Специалисты высокого уровня'
                ),
                new Maturity(
                        textcode: 'EXPERT',
                        title: 'Экпертный уровень'
                )
        ]
    }
}
