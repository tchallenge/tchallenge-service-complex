package ru.tchallenge.service.complex.common

import groovy.transform.CompileStatic

@CompileStatic
interface GenericBootstrap<E extends GenericEntity, ID extends Serializable> extends Orchestrated {

}
