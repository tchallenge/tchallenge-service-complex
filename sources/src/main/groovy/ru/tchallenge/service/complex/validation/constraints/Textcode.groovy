package ru.tchallenge.service.complex.validation.constraints

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

import org.hibernate.validator.constraints.Length

@Documented
@NotNull
@Length(min = 4, max = 16)
@Pattern(regexp = '[0-9A-Za-z\\-]+')
@Constraint(validatedBy = [])
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER])
@interface Textcode {

    String message() default ''

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []
}
