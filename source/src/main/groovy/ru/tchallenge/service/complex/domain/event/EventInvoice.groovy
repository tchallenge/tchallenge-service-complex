package ru.tchallenge.service.complex.domain.event

import groovy.transform.CompileStatic

import javax.validation.Valid

import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.domain.event.interval.EventIntervalInvoice
import ru.tchallenge.service.complex.validation.constraints.Caption
import ru.tchallenge.service.complex.validation.constraints.Filled
import ru.tchallenge.service.complex.validation.constraints.OptionalShortText
import ru.tchallenge.service.complex.validation.constraints.OrdinalId
import ru.tchallenge.service.complex.validation.constraints.Required
import ru.tchallenge.service.complex.validation.constraints.Textcode
import ru.tchallenge.service.complex.validation.groups.Create
import ru.tchallenge.service.complex.validation.groups.Edit
import ru.tchallenge.service.complex.validation.groups.Identify
import ru.tchallenge.service.complex.validation.groups.Status

@CompileStatic
class EventInvoice {

    @OrdinalId(groups = Identify)
    String id

    @Textcode(groups = [Create, Edit])
    String textcode

    @Caption(groups = [Create, Edit])
    String title

    @Caption(groups = [Create, Edit])
    String subtitle

    @OptionalShortText(groups = [Create, Edit])
    String description

    @OptionalShortText(groups = [Create, Edit])
    String greeting

    @Valid
    @Filled(groups = [Create, Edit])
    Collection<EventIntervalInvoice> intervals

    @Valid
    @Required(groups = [Create, Edit])
    EnumeratedInvoice category

    @Valid
    @Required(groups = Status)
    EnumeratedInvoice status
}
