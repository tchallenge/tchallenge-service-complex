package ru.tchallenge.service.complex.convention.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.core.annotation.AliasFor;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Entity
@Table
public @interface EnumeratedEntity {

    @AliasFor(annotation = Table.class, attribute = "name")
    String value();
}
