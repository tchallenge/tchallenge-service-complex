package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.task.snippet.style.TaskSnippetStyleRepository

@CompileStatic
@MapperComponent
class TaskSnippetMapper extends GenericMapper {

    @Autowired
    protected TaskSnippetStyleRepository taskSnippetStyleRepository

    TaskSnippet asEntity(TaskSnippetInvoice invoice) {
        return new TaskSnippet(
                content: invoice.content,
                stance: invoice.stance,
                style: EnumeratedHelper.one(invoice.style, taskSnippetStyleRepository)
        )
    }

    TaskSnippetInfo asInfo(TaskSnippet entity) {
        return new TaskSnippetInfo(
                content: entity.content,
                stance: entity.stance,
                style: EnumeratedHelper.one(entity.style)
        )
    }
}
