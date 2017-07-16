package ru.tchallenge.service.complex.security.rescue

import groovy.transform.CompileStatic

@CompileStatic
interface RescueFacade {

    void create(RescueInvoice invoice)
}
