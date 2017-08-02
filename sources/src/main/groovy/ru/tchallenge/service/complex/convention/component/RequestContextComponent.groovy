package ru.tchallenge.service.complex.convention.component

import groovy.transform.TypeChecked

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@TypeChecked
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Scope(scopeName = 'request', proxyMode = ScopedProxyMode.INTERFACES)
@interface RequestContextComponent {

}
