package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@CompileStatic
@PackageScope
@MapperComponent
class CandidateMapperBean extends GenericMapperBean implements CandidateMapper {

    @Override
    Candidate asEntity(Candidate entity, CandidateInvoice invoice) {
        def $result = entity ?: new Candidate()
        $result.with {
            id = invoice.id as Long ?: id
            github = invoice.github ?: github
            it
        }
    }

    @Override
    CandidateInfo asInfo(Candidate entity) {
        new CandidateInfo(
                github: entity.github
        )
    }
}
