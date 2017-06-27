package ru.tchallenge.service.complex.domain.maturity

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrap
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class MaturityBootstrap extends GenericEnumeratedBootstrap<Maturity> {

    @Override
    protected Collection<Maturity> enumeratedEntities() {
        return [
                new Maturity(
                        textcode: "STUDENT",
                        title: "Студенты, начало карьеры"
                ),
                new Maturity(
                        textcode: "JUNIOR",
                        title: "Младшие специалисты"
                ),
                new Maturity(
                        textcode: "INTERMEDIATE",
                        title: "Специалисты с практическим опытом"
                ),
                new Maturity(
                        textcode: "SENIOR",
                        title: "Специалисты высокого уровня"
                ),
                new Maturity(
                        textcode: "EXPERT",
                        title: "Экпертный уровень"
                )
        ]
    }
}
