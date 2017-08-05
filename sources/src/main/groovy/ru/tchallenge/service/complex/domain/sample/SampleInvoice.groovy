package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

import ru.tchallenge.service.complex.validation.constraints.Textcode
import ru.tchallenge.service.complex.validation.groups.Create
import ru.tchallenge.service.complex.validation.groups.Edit
import ru.tchallenge.service.complex.validation.groups.Identify

@CompileStatic
class SampleInvoice {

    @NotNull(groups = Identify)
    String id

    @Textcode(groups = [Create, Edit])
    String textcode

    @NotNull(groups = [Create, Edit])
    @Size(min = 4, max = 16, message = 'title must be not less than 4 and not much than 16 symbols in length', groups = [Create, Edit])
    @Pattern(regexp = '[A-Z, a-z]+', groups = [Create, Edit])
    String title

    @Size(min = 0, max = 256, groups = [Create, Edit])
    String description
}
