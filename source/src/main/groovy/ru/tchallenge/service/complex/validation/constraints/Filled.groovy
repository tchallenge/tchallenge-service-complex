package ru.tchallenge.service.complex.validation.constraints

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Size

@Documented
@Required
@Size(min = 1)
@Constraint(validatedBy = [])
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER])
@interface Filled {

    String message() default ''

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []
}
