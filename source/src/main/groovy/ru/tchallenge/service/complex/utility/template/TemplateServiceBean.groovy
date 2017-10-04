package ru.tchallenge.service.complex.utility.template

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

import org.springframework.beans.factory.annotation.Autowired

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template

import ru.tchallenge.service.complex.common.GenericComponentBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@TypeChecked
@PackageScope
@ServiceComponent
class TemplateServiceBean extends GenericComponentBean implements TemplateService {

    @Autowired
    Map<String, Template> cache

    @Autowired
    Handlebars handlebars

    @Autowired
    TemplateLayout layout

    @Override
    String render(String name, Object model) {
        try {
            template(name).apply(model)
        } catch (Exception exception) {
            throw new RuntimeException(exception)
        }
    }

    private Template template(String name) {
        layout.cacheEnabled ? cache.computeIfAbsent(name, this.&compile) : compile(name)
    }

    private Template compile(final String name) {
        try {
            handlebars.compile(name)
        } catch (final Exception exception) {
            throw new RuntimeException(exception)
        }
    }
}
