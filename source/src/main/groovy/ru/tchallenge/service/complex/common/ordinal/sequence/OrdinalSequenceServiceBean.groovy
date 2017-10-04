package ru.tchallenge.service.complex.common.ordinal.sequence

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.convention.component.ServiceComponent

@CompileStatic
@PackageScope
@ServiceComponent
class OrdinalSequenceServiceBean extends GenericServiceBean implements OrdinalSequenceService {

    @Autowired
    OrdinalSequenceRepository ordinalSequenceRepository

    @Override
    Long nextValue(String id) {
        def $entity = ordinalSequenceRepository.findById(id)
        if (!$entity) {
            throw new RuntimeException("unknown ordinal sequence ID: ${id}")
        }
        def $current = $entity.currentValue
        def $result = $current != null ? $current + $entity.step : $entity.initialValue
        $entity.currentValue = $result
        ordinalSequenceRepository.save($entity)
        return $result
    }
}
