package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.task.category.TaskCategoryRepository
import ru.tchallenge.service.complex.domain.task.difficulty.TaskDifficultyRepository
import ru.tchallenge.service.complex.domain.task.expectation.TaskExpectationRepository
import ru.tchallenge.service.complex.domain.task.input.TaskInput
import ru.tchallenge.service.complex.domain.task.input.TaskInputInfo
import ru.tchallenge.service.complex.domain.task.input.TaskInputInvoice
import ru.tchallenge.service.complex.domain.task.input.TaskInputMapper
import ru.tchallenge.service.complex.domain.task.option.TaskOption
import ru.tchallenge.service.complex.domain.task.option.TaskOptionInfo
import ru.tchallenge.service.complex.domain.task.option.TaskOptionInvoice
import ru.tchallenge.service.complex.domain.task.option.TaskOptionMapper
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippet
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippetInfo
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippetInvoice
import ru.tchallenge.service.complex.domain.task.snippet.TaskSnippetMapper
import ru.tchallenge.service.complex.domain.task.status.TaskStatusRepository

@CompileStatic
@PackageScope
@MapperComponent
class TaskMapperBean extends GenericMapperBean implements TaskMapper {

    @Autowired
    TaskCategoryRepository taskCategoryRepository

    @Autowired
    TaskDifficultyRepository taskDifficultyRepository

    @Autowired
    TaskExpectationRepository taskExpectationRepository

    @Autowired
    TaskStatusRepository taskStatusRepository

    @Autowired
    TaskInputMapper taskInputMapper

    @Autowired
    TaskOptionMapper taskOptionMapper

    @Autowired
    TaskSnippetMapper taskSnippetMapper

    @Override
    Task asEntity(TaskInvoice invoice) {
        asEntity(null, invoice)
    }

    @Override
    Task asEntity(Task entity, TaskInvoice invoice) {
        def $result = entity ?: new Task()
        $result.with {
            id = invoice.id as Long ?: id
            title = invoice.title ?: title
            introduction = invoice.introduction ?: introduction
            question = invoice.question ?: question
            inputs = invoice.inputs ? mapInputInvoices(invoice.inputs) : inputs
            options = invoice.options ? mapOptionInvoices(invoice.options) : options
            snippets = invoice.snippets ? mapSnippetInvoices(invoice.snippets) : snippets
            categories = invoice.categories ? enumerateds.some(taskCategoryRepository, invoice.categories) : categories
            difficulty = invoice.difficulty ? enumerateds.one(taskDifficultyRepository, invoice.difficulty) : difficulty
            expectation = invoice.expectation ? enumerateds.one(taskExpectationRepository, invoice.expectation) : expectation
            status = invoice.status ? enumerateds.one(taskStatusRepository, invoice.status) : status
            it
        }
    }

    @Override
    TaskInfo asInfo(Task entity) {
        new TaskInfo(
                id: entity.id as String,
                title: entity.title,
                introduction: entity.introduction,
                question: entity.question,
                inputs: mapInputs(entity.inputs),
                options: mapOptions(entity.options),
                snippets: mapSnippets(entity.snippets),
                categories: enumerateds.infos(entity.categories),
                difficulty: enumerateds.info(entity.difficulty),
                expectation: enumerateds.info(entity.expectation),
                status: enumerateds.info(entity.status)
        )
    }

    private Collection<TaskInputInfo> mapInputs(Collection<TaskInput> entities) {
        mapCollection(entities) { TaskInput it -> taskInputMapper.asInfo(it) }
    }

    private Collection<TaskInput> mapInputInvoices(Collection<TaskInputInvoice> invoices) {
        mapCollection(invoices) { TaskInputInvoice it -> taskInputMapper.asEntity(it) }
    }

    private Collection<TaskOptionInfo> mapOptions(Collection<TaskOption> entities) {
        mapCollection(entities) { TaskOption it -> taskOptionMapper.asInfo(it) }
    }

    private Collection<TaskOption> mapOptionInvoices(Collection<TaskOptionInvoice> invoices) {
        mapCollection(invoices) { TaskOptionInvoice it -> taskOptionMapper.asEntity(it) }
    }

    private Collection<TaskSnippetInfo> mapSnippets(Collection<TaskSnippet> entities) {
        mapCollection(entities) { TaskSnippet it -> taskSnippetMapper.asInfo(it) }
    }

    private Collection<TaskSnippet> mapSnippetInvoices(Collection<TaskSnippetInvoice> invoices) {
        mapCollection(invoices) { TaskSnippetInvoice it -> taskSnippetMapper.asEntity(it) }
    }
}
