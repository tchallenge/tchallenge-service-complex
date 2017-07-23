package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.EnumeratedInvoice
import ru.tchallenge.service.complex.common.search.SearchInfo
import ru.tchallenge.service.complex.convention.component.ServiceComponent

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
