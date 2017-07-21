package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.domain.event.category.EventCategory
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.interval.EventInterval
import ru.tchallenge.service.complex.domain.event.status.EventStatus
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository

@CompileStatic
@PackageScope
@BootstrapComponent
class EventBootstrapBean extends GenericOrdinalBootstrapBean<Event> implements EventBootstrap {

    @Autowired
    EventCategoryRepository eventCategoryRepository

    @Autowired
    EventStatusRepository eventStatusRepository

    @Override
    protected Collection<Event> entities() {
        [
                joker2016(),
                findIt2017(),
                heisenbug2017(),
                javaschool(),
                sqaDays2017(),
                joker2017()
        ]
    }

    private Event joker2016() {
        new Event(
                textcode: 'joker2016',
                title: 'Joker 2016',
                subtitle: 'Для гостей и участников конференции',
                description: 'Ежегодная Java-конференция',
                greeting: 'Добро пожаловать!',
                intervals: [
                        new EventInterval(
                                since: instant('2016-10-14T09:00:00+03:00'),
                                until: instant('2016-10-14T16:00:00+03:00')
                        ),
                        new EventInterval(
                                since: instant('2016-10-15T09:00:00+03:00'),
                                until: instant('2016-10-15T16:00:00+03:00')
                        )
                ],
                category: category('FORUM'),
                status: status('APPROVED')
        )
    }

    private Event findIt2017() {
        new Event(
                textcode: 'findIt2017',
                title: 'Найти IT 2017',
                subtitle: 'Для гостей и участников конференции',
                description: 'Ежегодная конференция для молодых IT-специалистов',
                greeting: 'Добро пожаловать!',
                intervals: [
                        new EventInterval(
                                since: instant('2017-04-01T09:00:00+03:00'),
                                until: instant('2017-04-01T15:00:00+03:00')
                        )
                ],
                category: category('FORUM'),
                status: status('APPROVED')
        )
    }

    private Event heisenbug2017() {
        new Event(
                textcode: 'heisenbug2017',
                title: 'Heisenbug 2017',
                subtitle: 'Для гостей и участников конференции',
                description: 'Ежегодная конференция, посвященное тестированию ПО',
                greeting: 'Добро пожаловать!',
                intervals: [
                        new EventInterval(
                                since: instant('2017-06-04T09:00:00+03:00'),
                                until: instant('2017-06-04T16:00:00+03:00')
                        )
                ],
                category: category('FORUM'),
                status: status('APPROVED')
        )
    }

    private Event javaschool() {
        new Event(
                textcode: 'javaschool',
                title: 'Java-школа T-Systems',
                subtitle: 'Для поступающих в Java-школу',
                description: 'Отборочное тестирование для кандидатов',
                greeting: 'Пройдите тестирование',
                intervals: [
                        new EventInterval(
                                since: instant('2017-06-26T12:00:00+03:00'),
                                until: instant('2017-06-26T15:00:00+03:00')
                        )
                ],
                category: category('SCREENING'),
                status: status('APPROVED')
        )
    }

    private Event sqaDays2017() {
        new Event(
                textcode: 'sqaDays2017',
                title: 'SQA Days 2017',
                subtitle: 'Для гостей и участников конференции',
                description: 'Ежегодная конференция, посвященное тестированию ПО',
                greeting: 'Добро пожаловать!',
                intervals: [
                        new EventInterval(
                                since: instant('2017-11-14T09:00:00+03:00'),
                                until: instant('2017-11-14T16:00:00+03:00')
                        )
                ],
                category: category('FORUM'),
                status: status('APPROVED')
        )
    }

    private Event joker2017() {
        new Event(
                textcode: 'joker2017',
                title: 'Joker 2017',
                subtitle: 'Для гостей и участников конференции',
                description: 'Ежегодная Java-конференция',
                greeting: 'Добро пожаловать!',
                intervals: [
                        new EventInterval(
                                since: instant('2017-11-03T09:00:00+03:00'),
                                until: instant('2017-11-03T16:00:00+03:00')
                        ),
                        new EventInterval(
                                since: instant('2017-11-04T09:00:00+03:00'),
                                until: instant('2017-11-04T16:00:00+03:00')
                        )
                ],
                category: category('FORUM'),
                status: status('APPROVED')
        )
    }

    private EventCategory category(String textcode) {
        enumerateds.one(eventCategoryRepository, textcode)
    }

    private EventStatus status(String textcode) {
        enumerateds.one(eventStatusRepository, textcode)
    }
}
