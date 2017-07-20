package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInfo
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.task.category.TaskCategoryRepository
import ru.tchallenge.service.complex.domain.task.difficulty.TaskDifficultyRepository
import ru.tchallenge.service.complex.domain.task.expectation.TaskExpectationRepository
import ru.tchallenge.service.complex.domain.task.snippet.style.TaskSnippetStyleRepository
import ru.tchallenge.service.complex.domain.task.status.TaskStatusRepository
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.all
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.invoice
import static ru.tchallenge.service.complex.common.search.SearchTransformations.info
import static ru.tchallenge.service.complex.common.search.SearchTransformations.normalizePattern
import static ru.tchallenge.service.complex.common.search.SearchTransformations.pageable

@CompileStatic
@ServiceComponent
class TaskService extends GenericServiceBean {

    @Autowired
    protected TaskMapper taskMapper

    @Autowired
    protected TaskPersister taskPersister

    @Autowired
    protected TaskRepository taskRepository

    @Autowired
    protected TaskCategoryRepository taskCategoryRepository

    @Autowired
    protected TaskDifficultyRepository taskDifficultyRepository

    @Autowired
    protected TaskExpectationRepository taskExpectationRepository

    @Autowired
    protected TaskSnippetStyleRepository taskSnippetStyleRepository

    @Autowired
    protected TaskStatusRepository taskStatusRepository

    TaskInfo create(TaskInvoice invoice) {
        def task = taskMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        return saveAndInfo(task)
    }

    Collection<EnumeratedInfo> getAllCategories() {
        return all(taskCategoryRepository)
    }

    Collection<EnumeratedInfo> getAllDifficulties() {
        return all(taskDifficultyRepository)
    }

    Collection<EnumeratedInfo> getAllExpectations() {
        return all(taskExpectationRepository)
    }

    Collection<EnumeratedInfo> getAllSnippetStyles() {
        return all(taskSnippetStyleRepository)
    }

    Collection<EnumeratedInfo> getAllStatuses() {
        return all(taskStatusRepository)
    }

    TaskInfo getById(String id) {
        return info(taskById(id))
    }

    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice) {
        Page<Task> page = taskRepository.findPage(
                normalizePattern(invoice.filterTextPattern),
                invoice.filterStatusTextcodes,
                pageable(invoice)
        )
        return info(invoice, page) {
            Task it -> info(it)
        }
    }

    TaskInfo update(TaskInvoice invoice) {
        def entity = taskById(invoice.id)
        def trimmedInvoice = invoice.with {
            id = null
            it
        }
        def mergedEntity = taskMapper.asEntity(entity, trimmedInvoice)
        return saveAndInfo(mergedEntity)
    }

    private Task taskById(String id) {
        def result = taskRepository.findById(id as Long)
        if (!result) {
            throw unknownTask()
        }
        return result
    }

    private TaskInfo info(Task entity) {
        return taskMapper.asInfo(entity)
    }

    private Task save(Task entity) {
        return taskPersister.save(entity)
    }

    private TaskInfo saveAndInfo(Task entity) {
        return info(save(entity))
    }

    private static EnumeratedInvoice initialStatus() {
        return invoice("CREATED")
    }

    private static RuntimeException unknownTask() {
        return new RuntimeException("referenced task does not exist")
    }
}
