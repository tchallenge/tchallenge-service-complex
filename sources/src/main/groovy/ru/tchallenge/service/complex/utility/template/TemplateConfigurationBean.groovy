package ru.tchallenge.service.complex.utility.template

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

import java.util.concurrent.ConcurrentHashMap

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.TemplateLoader

import ru.tchallenge.service.complex.common.GenericComponentBean

@TypeChecked
@PackageScope
@Configuration
class TemplateConfigurationBean extends GenericComponentBean {

    @Value('${tchallenge.mail.template.cache.enabled}')
    Boolean cacheEnabled

    @Value('${tchallenge.mail.template.prefix}')
    String prefix

    @Value('${tchallenge.mail.template.suffix}')
    String suffix

    @Bean
    Map<String, Template> templateCache() {
        new ConcurrentHashMap<String, Template>()
    }

    @Bean
    TemplateLoader templateLoader() {
        new ClassPathTemplateLoader(prefix, suffix)
    }

    @Bean
    Handlebars templateEngine(TemplateLoader loader) {
        new Handlebars(loader);
    }

    @Bean
    TemplateLayout templateLayout() {
        new TemplateLayout(
                cacheEnabled: cacheEnabled
        )
    }
}
