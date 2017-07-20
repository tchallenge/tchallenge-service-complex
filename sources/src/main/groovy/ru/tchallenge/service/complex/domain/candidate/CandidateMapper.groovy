package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class CandidateMapper extends GenericMapperBean {

    Candidate asEntity(Candidate entity, CandidateInvoice invoice) {
        entity = entity ?: new Candidate()
        return entity.with {
            id = invoice.id as Long ?: id
            github = invoice.github ?: github
            it
        }
    }

    CandidateInfo asInfo(Candidate entity) {
        return new CandidateInfo(
                github: entity.github
        )
    }
}
