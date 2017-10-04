package ru.tchallenge.service.complex.validation.constraints

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

import ru.tchallenge.service.complex.common.Interval

@CompileStatic
@PackageScope
class TimeIntervalValidator implements ConstraintValidator<TimeInterval, Interval> {

    @Override
    void initialize(TimeInterval constraint) {

    }

    @Override
    boolean isValid(Interval value, ConstraintValidatorContext context) {
        value.since < value.until
    }
}
