package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformation.*
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
                style: one(taskSnippetStyleRepository, invoice.style)
        )
    }

    TaskSnippetInfo asInfo(TaskSnippet entity) {
        return new TaskSnippetInfo(
                content: entity.content,
                stance: entity.stance,
                style: info(entity.style)
        )
    }
}
