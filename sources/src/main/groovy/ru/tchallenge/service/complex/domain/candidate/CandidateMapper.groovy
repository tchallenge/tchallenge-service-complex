package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class CandidateMapper extends GenericMapper {

    CandidateInfo asInfo(Candidate entity) {
        return new CandidateInfo(
                github: entity.github
        )
    }

    Candidate merge(Candidate entity, CandidateInvoice invoice) {
        entity = entity ?: new Candidate()
        return entity.with {
            id = invoice.id ? invoice.id as Long : id
            it
        }
    }
}
