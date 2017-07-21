package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

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

@CompileStatic
@PackageScope
@ServiceComponent
class TaskServiceBean extends GenericServiceBean implements TaskService {

    @Autowired
    TaskMapper taskMapper

    @Autowired
    TaskPersister taskPersister

    @Autowired
    TaskRepository taskRepository

    @Autowired
    TaskCategoryRepository taskCategoryRepository

    @Autowired
    TaskDifficultyRepository taskDifficultyRepository

    @Autowired
    TaskExpectationRepository taskExpectationRepository

    @Autowired
    TaskSnippetStyleRepository taskSnippetStyleRepository

    @Autowired
    TaskStatusRepository taskStatusRepository

    @Override
    TaskInfo create(TaskInvoice invoice) {
        def $task = taskMapper.asEntity(invoice.with {
            id = null
            status = initialStatus()
            it
        })
        saveAndInfo($task)
    }

    @Override
    Collection<EnumeratedInfo> getAllCategories() {
        enumerateds.all(taskCategoryRepository)
    }

    @Override
    Collection<EnumeratedInfo> getAllDifficulties() {
        enumerateds.all(taskDifficultyRepository)
    }

    @Override
    Collection<EnumeratedInfo> getAllExpectations() {
        enumerateds.all(taskExpectationRepository)
    }

    @Override
    Collection<EnumeratedInfo> getAllSnippetStyles() {
        enumerateds.all(taskSnippetStyleRepository)
    }

    @Override
    Collection<EnumeratedInfo> getAllStatuses() {
        enumerateds.all(taskStatusRepository)
    }

    @Override
    TaskInfo getById(String id) {
        info(taskById(id))
    }

    @Override
    SearchInfo<TaskInfo> search(TaskSearchInvoice invoice) {
        Page<Task> $page = taskRepository.findPage(
                searches.normalizePattern(invoice.filterTextPattern),
                invoice.filterStatusTextcodes,
                searches.pageable(invoice)
        )
        searches.info(invoice, $page) { Task it -> info(it) }
    }

    @Override
    TaskInfo update(TaskInvoice invoice) {
        def $entity = taskById(invoice.id)
        def $trimmedInvoice = invoice.with {
            id = null
            it
        }
        def $mergedEntity = taskMapper.asEntity($entity, $trimmedInvoice)
        saveAndInfo($mergedEntity)
    }

    private Task taskById(String id) {
        def $result = taskRepository.findById(id as Long)
        if (!$result) {
            throw unknownTask()
        }
        $result
    }

    private TaskInfo info(Task entity) {
        taskMapper.asInfo(entity)
    }

    private Task save(Task entity) {
        taskPersister.save(entity)
    }

    private TaskInfo saveAndInfo(Task entity) {
        info(save(entity))
    }

    private static EnumeratedInvoice initialStatus() {
        enumerateds.invoice('CREATED')
    }

    private static RuntimeException unknownTask() {
        new RuntimeException('referenced task does not exist')
    }
}
