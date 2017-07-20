package ru.tchallenge.service.complex.domain.task

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent
import ru.tchallenge.service.complex.domain.task.category.TaskCategory
import ru.tchallenge.service.complex.domain.task.category.TaskCategoryRepository
import ru.tchallenge.service.complex.domain.task.difficulty.TaskDifficulty
import ru.tchallenge.service.complex.domain.task.difficulty.TaskDifficultyRepository
import ru.tchallenge.service.complex.domain.task.expectation.TaskExpectation
import ru.tchallenge.service.complex.domain.task.expectation.TaskExpectationRepository
import ru.tchallenge.service.complex.domain.task.input.TaskInput
import ru.tchallenge.service.complex.domain.task.status.TaskStatus
import ru.tchallenge.service.complex.domain.task.status.TaskStatusRepository
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.one
import static ru.tchallenge.service.complex.common.enumerated.EnumeratedTransformations.some

@CompileStatic
@BootstrapComponent
class TaskBootstrap extends GenericOrdinalBootstrapBean<Task> {

    @Autowired
    protected TaskCategoryRepository taskCategoryRepository

    @Autowired
    protected TaskDifficultyRepository taskDifficultyRepository

    @Autowired
    protected TaskExpectationRepository taskExpectationRepository

    @Autowired
    protected TaskStatusRepository taskStatusRepository

    @Override
    protected Collection<Task> entities() {
        return [
                task1()
        ]
    }

    private Task task1() {
        return new Task(
                title: "Задача 1",
                introduction: "вопрос к задаче 1",
                question: "вопрос к задаче 1",
                inputs: [
                        new TaskInput(
                                content: "ответ к задаче 1",
                                regex: flag(false),
                                stance: 1
                        )
                ],
                categories: categories("JAVA", "TEST"),
                difficulty: difficulty("MEDIUM"),
                expectation: expectation("SMALLINPUT"),
                status: status("APPROVED")
        )
    }

    private Collection<TaskCategory> categories(String... textcodes) {
        return some(taskCategoryRepository, textcodes)
    }

    private TaskDifficulty difficulty(String textcode) {
        return one(taskDifficultyRepository, textcode)
    }

    private TaskExpectation expectation(String textcode) {
        return one(taskExpectationRepository, textcode)
    }

    private TaskStatus status(String textcode) {
        return one(taskStatusRepository, textcode)
    }
}
