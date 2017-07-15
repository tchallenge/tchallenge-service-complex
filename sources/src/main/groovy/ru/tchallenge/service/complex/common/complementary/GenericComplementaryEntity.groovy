package ru.tchallenge.service.complex.common.complementary

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

import ru.tchallenge.service.complex.common.GenericEntity

@CompileStatic
@MappedSuperclass
abstract class GenericComplementaryEntity extends GenericEntity<String> {

    @Id
    @Column(name = "id")
    String id

    @Override
    protected void onInsert() {
        super.onInsert()
        id = uuid
    }
}
