package ru.tchallenge.service.complex.domain.sample.status

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Table

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedEntity

@CompileStatic
@Entity
@Table(name = 'sample_status')
class SampleStatus extends GenericEnumeratedEntity {

}
