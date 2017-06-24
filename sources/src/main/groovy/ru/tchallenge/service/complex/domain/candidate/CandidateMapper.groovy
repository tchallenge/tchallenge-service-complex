package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@MapperComponent
class CandidateMapper extends GenericMapper {

    Candidate asEntity(CandidateInvoice invoice) {
        return new Candidate(
                github: invoice.github
        )
    }

    CandidateInfo asInfo(Candidate entity) {
        return new CandidateInfo(
                github: entity.github
        )
    }
}