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
import ru.tchallenge.service.complex.validation.groups.Update
import ru.tchallenge.service.complex.validation.groups.UpdateCommon
import ru.tchallenge.service.complex.validation.groups.UpdateStatus

@CompileStatic
class EventInvoice {

    @OrdinalId(groups = Update)
    String id

    @Textcode(groups = [Create, UpdateCommon])
    String textcode

    @Caption(groups = [Create, UpdateCommon])
    String title

    @Caption(groups = [Create, UpdateCommon])
    String subtitle

    @OptionalShortText(groups = [Create, UpdateCommon])
    String description

    @OptionalShortText(groups = [Create, UpdateCommon])
    String greeting

    @Valid
    @Filled(groups = [Create, UpdateCommon])
    Collection<EventIntervalInvoice> intervals

    @Valid
    @Required(groups = [Create, UpdateCommon])
    EnumeratedInvoice category

    @Valid
    @Required(groups = UpdateStatus)
    EnumeratedInvoice status
}
