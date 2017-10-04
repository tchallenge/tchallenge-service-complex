package ru.tchallenge.service.complex.validation.constraints

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint
import javax.validation.Payload

@Documented
@Constraint(validatedBy = TimeIntervalValidator)
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE])
@interface TimeInterval {

    String message() default 'value of Since must be earlier that value of Until'

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []
}
