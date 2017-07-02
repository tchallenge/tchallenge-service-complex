package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
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
import ru.tchallenge.service.complex.utility.miscellaneous.Foundamentals
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.info
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.infos
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.one
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.some

@CompileStatic
@MapperComponent
class TaskMapper extends GenericMapper {

    @Autowired
    protected TaskCategoryRepository taskCategoryRepository

    @Autowired
    protected TaskDifficultyRepository taskDifficultyRepository

    @Autowired
    protected TaskExpectationRepository taskExpectationRepository

    @Autowired
    protected TaskStatusRepository taskStatusRepository

    @Autowired
    protected TaskInputMapper taskInputMapper

    @Autowired
    protected TaskOptionMapper taskOptionMapper

    @Autowired
    protected TaskSnippetMapper taskSnippetMapper

    Task asEntity(TaskInvoice invoice) {
        return asEntity(null, invoice)
    }

    Task asEntity(Task entity, TaskInvoice invoice) {
        entity = entity ?: new Task()
        return entity.with {
            id = invoice.id as Long ?: id
            title = invoice.title ?: title
            introduction = invoice.introduction ?: introduction
            question = invoice.question ?: question
            inputs = invoice.inputs ? mapInputInvoices(invoice.inputs) : inputs
            options = invoice.options ? mapOptionInvoices(invoice.options) : options
            snippets = invoice.snippets ? mapSnippetInvoices(invoice.snippets) : snippets
            categories = invoice.categories ? some(taskCategoryRepository, invoice.categories) : categories
            difficulty = invoice.difficulty ? one(taskDifficultyRepository, invoice.difficulty) : difficulty
            expectation = invoice.expectation ? one(taskExpectationRepository, invoice.expectation) : expectation
            status = invoice.status ? one(taskStatusRepository, invoice.status) : status
            it
        }
    }

    TaskInfo asInfo(Task entity) {
        return new TaskInfo(
                id: entity.id as String,
                title: entity.title,
                introduction: entity.introduction,
                question: entity.question,
                inputs: mapInputs(entity.inputs),
                options: mapOptions(entity.options),
                snippets: mapSnippets(entity.snippets),
                categories: infos(entity.categories),
                difficulty: info(entity.difficulty),
                expectation: info(entity.expectation),
                status: info(entity.status)
        )
    }

    private Collection<TaskInputInfo> mapInputs(Collection<TaskInput> entities) {
        return Foundamentals.mapCollection(entities) { TaskInput it -> taskInputMapper.asInfo(it) }
    }

    private Collection<TaskInput> mapInputInvoices(Collection<TaskInputInvoice> invoices) {
        return Foundamentals.mapCollection(invoices) { TaskInputInvoice it -> taskInputMapper.asEntity(it) }
    }

    private Collection<TaskOptionInfo> mapOptions(Collection<TaskOption> entities) {
        return Foundamentals.mapCollection(entities) { TaskOption it -> taskOptionMapper.asInfo(it) }
    }

    private Collection<TaskOption> mapOptionInvoices(Collection<TaskOptionInvoice> invoices) {
        return Foundamentals.mapCollection(invoices) { TaskOptionInvoice it -> taskOptionMapper.asEntity(it) }
    }

    private Collection<TaskSnippetInfo> mapSnippets(Collection<TaskSnippet> entities) {
        return Foundamentals.mapCollection(entities) { TaskSnippet it -> taskSnippetMapper.asInfo(it) }
    }

    private Collection<TaskSnippet> mapSnippetInvoices(Collection<TaskSnippetInvoice> invoices) {
        return Foundamentals.mapCollection(invoices) { TaskSnippetInvoice it -> taskSnippetMapper.asEntity(it) }
    }
}
