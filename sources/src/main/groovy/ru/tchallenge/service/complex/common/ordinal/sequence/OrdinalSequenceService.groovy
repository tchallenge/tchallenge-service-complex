package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericService
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class OrdinalSequenceService extends GenericService {

    @Autowired
    protected OrdinalSequenceRepository ordinalSequenceRepository

    Long nextValue(String id) {
        def entity = ordinalSequenceRepository.findById(id)
        if (entity == null) {
            throw new RuntimeException("illegal ordinal sequence ID: " + id)
        }
        def result = entity.currentValue + 1
        entity.currentValue = result
        ordinalSequenceRepository.save(entity)
        return result
    }
}
