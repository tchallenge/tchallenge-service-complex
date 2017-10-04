package ru.tchallenge.service.complex.utility.mail

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable(knownImmutableClasses = [Object])
class Mail {

    Object payload
    String subject
    String target
    String template
}
