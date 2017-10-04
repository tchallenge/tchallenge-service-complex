package ru.tchallenge.service.complex.utility.mail

import groovy.transform.CompileStatic

@CompileStatic
interface MailService {

    void sendAsync(Mail mail)
}
