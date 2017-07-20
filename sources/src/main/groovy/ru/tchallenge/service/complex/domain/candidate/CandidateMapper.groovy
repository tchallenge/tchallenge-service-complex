package ru.tchallenge.service.complex.domain.candidate

import groovy.transform.CompileStatic

@CompileStatic
interface CandidateMapper {

    Candidate asEntity(Candidate entity, CandidateInvoice invoice)

    CandidateInfo asInfo(Candidate entity)
}
