package ru.tchallenge.service.complex.utility.mail

import groovy.transform.CompileStatic
import groovy.transform.Immutable

@CompileStatic
@Immutable
class Mail {

    Collection<MailAttachment> attachments
    String content
    String subject
    String target
}
