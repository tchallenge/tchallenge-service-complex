package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.TypeChecked

@TypeChecked
interface CandidateMapper {

    CandidateInfo asInfo(Candidate entity)
}
