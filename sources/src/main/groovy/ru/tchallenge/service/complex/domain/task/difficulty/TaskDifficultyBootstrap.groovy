package ru.tchallenge.service.complex.domain.task.difficulty

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedBootstrapBean
import ru.tchallenge.service.complex.convention.component.BootstrapComponent

@CompileStatic
@BootstrapComponent
class TaskDifficultyBootstrap extends GenericEnumeratedBootstrapBean<TaskDifficulty> {

    @Override
    protected Collection<TaskDifficulty> enumeratedEntities() {
        return [
                new TaskDifficulty(
                        textcode: "ELEMENTARY",
                        title: "Простейшие задачи"
                ),
                new TaskDifficulty(
                        textcode: "EASY",
                        title: "Относительно легкие"
                ),
                new TaskDifficulty(
                        textcode: "MEDIUM",
                        title: "Умеренно сложные"
                ),
                new TaskDifficulty(
                        textcode: "HARD",
                        title: "Сложные"
                ),
                new TaskDifficulty(
                        textcode: "ULTIMATE",
                        title: "Задачи повышенной сложности"
                )
        ]
    }
}
