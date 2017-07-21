package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrap
import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.domain.event.category.EventCategory
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.interval.EventInterval
import ru.tchallenge.service.complex.domain.event.status.EventStatus
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository

@CompileStatic
interface EventBootstrap extends GenericOrdinalBootstrap<Event> {

}
