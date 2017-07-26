package ru.tchallenge.service.complex.domain.sample

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

import ru.tchallenge.service.complex.common.GenericEntity
import ru.tchallenge.service.complex.common.timestamp.TimestampedEntity

@CompileStatic
@Entity
@Table(name = 'sample')
class Sample extends GenericEntity<String> implements TimestampedEntity {

    @Id
    @Column(name = 'id', nullable = false, unique = true, updatable = false)
    String id

    @Column(name = 'title', nullable = false)
    String title

    @Column(name = 'description')
    String description

    @Override
    protected void onInsert() {
        super.onInsert()
        id = uuid
    }
}
