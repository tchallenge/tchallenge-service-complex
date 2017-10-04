package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@TypeChecked
@PackageScope
@MapperComponent
class CandidateMapperBean extends GenericMapperBean implements CandidateMapper {

    @Override
    CandidateInfo asInfo(Candidate entity) {
        new CandidateInfo(
                github: entity.github
        )
    }
}
