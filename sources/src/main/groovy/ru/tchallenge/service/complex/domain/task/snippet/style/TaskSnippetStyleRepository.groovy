package ru.tchallenge.service.complex.domain.task.snippet.style

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.domain.shared.enumerated.EnumeratedAggregationSource

@CompileStatic
@EnumeratedAggregationSource('task.snippet.style')
interface TaskSnippetStyleRepository extends GenericEnumeratedRepository<TaskSnippetStyle> {

}
