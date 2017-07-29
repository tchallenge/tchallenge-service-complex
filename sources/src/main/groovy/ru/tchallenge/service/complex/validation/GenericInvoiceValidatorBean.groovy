package ru.tchallenge.service.complex.validation

import groovy.transform.CompileStatic

import javax.validation.groups.Default

import org.springframework.validation.Errors

@CompileStatic
abstract class GenericInvoiceValidatorBean<T> implements GenericInvoiceValidator {

    @Override
    boolean supports(Class<?> type) {
        T.class.isAssignableFrom(type)
    }

    @Override
    void validate(Object target, Errors errors) {
        if (shouldProceed(errors)) {
            validate(target, errors, groups())
        }
    }

    @Override
    void validate(Object target, Errors errors, Object... hints) {
        if (shouldProceed(errors)) {
            validate(target, errors, groups(hints))
        }
    }

    protected abstract void validate(Object target, Errors errors, Set<Class<?>> groups)

    protected boolean shouldProceed(Errors errors) {
        return !errors.hasErrors()
    }

    private static Set<Class<?>> groups(Object... hints) {
        def $result = new HashSet<Class<?>>()
        hints.each { Object it ->
            if (validationGroup(it)) {
                $result.add(it as Class<?>)
            }
        }
        $result
    }

    private static boolean validationGroup(Object object) {
        object instanceof Class<?> && Default.class.isAssignableFrom(object as Class<?>)
    }
}
