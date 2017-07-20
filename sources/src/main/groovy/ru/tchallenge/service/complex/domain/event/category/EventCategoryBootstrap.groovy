package ru.tchallenge.service.complex.domain.event.category

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class EventCategoryBootstrap extends GenericEnumeratedBootstrapBean<EventCategory> {

    @Override
    protected Collection<EventCategory> enumeratedEntities() {
        return [
                new EventCategory(
                        textcode: "FORUM",
                        title: "Конференеция, публичное мероприятие"
                ),
                new EventCategory(
                        textcode: "INTERVIEW",
                        title: "Предварительный тест для интервью"
                ),
                new EventCategory(
                        textcode: "SCREENING",
                        title: "Скриннинг, вступительное задание"
                )
        ]
    }
}
