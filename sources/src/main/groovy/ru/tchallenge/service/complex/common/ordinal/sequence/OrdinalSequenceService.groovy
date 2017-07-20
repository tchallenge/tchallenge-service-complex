package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@ServiceComponent
class OrdinalSequenceService extends GenericServiceBean {

    @Autowired
    protected OrdinalSequenceRepository ordinalSequenceRepository

    Long nextValue(String id) {
        def entity = ordinalSequenceRepository.findById(id)
        if (entity == null) {
            throw new RuntimeException("unknown ordinal sequence ID: " + id)
        }
        def current = entity.currentValue
        def result = current != null ? current + entity.step : entity.initialValue
        entity.currentValue = result
        ordinalSequenceRepository.save(entity)
        return result
    }
}
