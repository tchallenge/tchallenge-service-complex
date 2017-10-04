package ru.tchallenge.service.complex.utility.template

import groovy.transform.TypeChecked

@TypeChecked
interface TemplateService {

    String render(String name, Object model)
}
