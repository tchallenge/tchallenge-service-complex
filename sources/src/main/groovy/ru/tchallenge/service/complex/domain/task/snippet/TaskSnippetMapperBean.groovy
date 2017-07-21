package ru.tchallenge.service.complex.domain.task.snippet

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.task.snippet.style.TaskSnippetStyleRepository

@CompileStatic
@PackageScope
@MapperComponent
class TaskSnippetMapperBean extends GenericMapperBean implements TaskSnippetMapper {

    @Autowired
    TaskSnippetStyleRepository taskSnippetStyleRepository

    @Override
    TaskSnippet asEntity(TaskSnippetInvoice invoice) {
        new TaskSnippet(
                content: invoice.content,
                stance: invoice.stance,
                style: enumerateds.one(taskSnippetStyleRepository, invoice.style)
        )
    }

    @Override
    TaskSnippetInfo asInfo(TaskSnippet entity) {
        new TaskSnippetInfo(
                content: entity.content,
                stance: entity.stance,
                style: enumerateds.info(entity.style)
        )
    }
}
