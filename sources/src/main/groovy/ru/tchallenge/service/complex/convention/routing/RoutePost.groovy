package ru.tchallenge.service.complex.convention.routing

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

import org.springframework.core.annotation.AliasFor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@TypeChecked
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RequestMapping(method = RequestMethod.POST)
@interface RoutePost {

    @TypeChecked(TypeCheckingMode.SKIP)
    @AliasFor(annotation = RequestMapping, attribute = 'path')
    String[] value() default []
}
