package ru.tchallenge.service.complex.utility.mail

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.utility.batch.BatchService

@CompileStatic
@PackageScope
@ServiceComponent
class MailServiceBean extends GenericServiceBean implements MailService {

    @Value('${tchallenge.mail.content-type}')
    String contentType

    @Value('${tchallenge.mail.encoding}')
    String encoding

    @Value('${tchallenge.mail.origin}')
    String origin

    @Autowired
    BatchService batchService

    @Autowired
    JavaMailSender mailSender

    @Override
    void sendAsync(Mail mail) {
        batchService.executeAsync(task(mail))
    }

    private Runnable task(Mail mail) {
        new Runnable() {
            void run() {
                def $message = mailSender.createMimeMessage()
                def $helper = new MimeMessageHelper($message, false, encoding)
                $message.setContent(mail.content, contentType)
                $helper.setTo(mail.target)
                $helper.setSubject(mail.subject)
                $helper.setFrom(origin)
                mailSender.send($message)
            }
        }
    }
}
