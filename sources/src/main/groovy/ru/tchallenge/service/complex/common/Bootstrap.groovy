package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
interface Bootstrap<E extends GenericEntity, ID extends Serializable> extends Orchestrated {

}
