package ru.tchallenge.service.complex.domain.shared.enumerated

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericServiceBean
import ru.tchallenge.service.complex.common.enumerated.GenericEnumeratedRepository
import ru.tchallenge.service.complex.convention.component.ServiceComponent
import ru.tchallenge.service.complex.domain.account.realm.AccountRealmRepository
import ru.tchallenge.service.complex.domain.account.status.AccountStatusRepository
import ru.tchallenge.service.complex.domain.account.verification.AccountVerificationRepository
import ru.tchallenge.service.complex.domain.assignment.status.AssignmentStatusRepository
import ru.tchallenge.service.complex.domain.employee.role.EmployeeRoleRepository
import ru.tchallenge.service.complex.domain.event.category.EventCategoryRepository
import ru.tchallenge.service.complex.domain.event.status.EventStatusRepository
import ru.tchallenge.service.complex.domain.maturity.MaturityRepository
import ru.tchallenge.service.complex.domain.robot.role.RobotRoleRepository
import ru.tchallenge.service.complex.domain.specialization.SpecializationRepository
import ru.tchallenge.service.complex.domain.task.category.TaskCategoryRepository
import ru.tchallenge.service.complex.domain.task.difficulty.TaskDifficultyRepository
import ru.tchallenge.service.complex.domain.task.expectation.TaskExpectationRepository
import ru.tchallenge.service.complex.domain.task.snippet.style.TaskSnippetStyleRepository
import ru.tchallenge.service.complex.domain.task.status.TaskStatusRepository
import ru.tchallenge.service.complex.domain.workbook.status.WorkbookStatusRepository

@CompileStatic
@PackageScope
@ServiceComponent
class EnumeratedServiceBean extends GenericServiceBean implements EnumeratedService {

    // TODO: implement automatic discovery of enumerateds

    @Autowired
    EnumeratedContextConfigurer enumeratedContextConfigurer

    @Autowired
    AccountRealmRepository accountRealmRepository

    @Autowired
    AccountStatusRepository accountStatusRepository

    @Autowired
    AccountVerificationRepository accountVerificationRepository

    @Autowired
    AssignmentStatusRepository assignmentStatusRepository

    @Autowired
    EmployeeRoleRepository employeeRoleRepository

    @Autowired
    EventCategoryRepository eventCategoryRepository

    @Autowired
    EventStatusRepository eventStatusRepository

    @Autowired
    MaturityRepository maturityRepository

    @Autowired
    RobotRoleRepository robotRoleRepository

    @Autowired
    SpecializationRepository specializationRepository

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

    @Autowired
    WorkbookStatusRepository workbookStatusRepository

    @Override
    Collection<EnumeratedAggregationInfo> getAll() {
        aggregations.values()
    }

    @Override
    EnumeratedAggregationInfo getAggregationByType(String type) {
        def $result = aggregations.get(type)
        if (!$result) {
            throw new RuntimeException('unknown aggregation type')
        }
        $result
    }

    private Map<String, EnumeratedAggregationInfo> getAggregations() {
        def $result = enumeratedContextConfigurer.aggregations
        if (!$result) {
            $result = [:]
            store($result, 'account.realm', accountRealmRepository)
            store($result, 'account.status', accountStatusRepository)
            store($result, 'account.verification', accountVerificationRepository)
            store($result, 'assignment.status', assignmentStatusRepository)
            store($result, 'employee.role', employeeRoleRepository)
            store($result, 'event.category', eventCategoryRepository)
            store($result, 'event.status', eventStatusRepository)
            store($result, 'maturity', maturityRepository)
            store($result, 'robot.role', robotRoleRepository)
            store($result, 'specialization', specializationRepository)
            store($result, 'task.category', taskCategoryRepository)
            store($result, 'task.difficulty', taskDifficultyRepository)
            store($result, 'task.snippet.style', taskSnippetStyleRepository)
            store($result, 'task.status', taskStatusRepository)
            store($result, 'workbook.status', workbookStatusRepository)
            enumeratedContextConfigurer.setAggregations($result)
        }
        $result
    }

    private static void store(Map<String, EnumeratedAggregationInfo> storage,
                              String type,
                              GenericEnumeratedRepository repository) {
        def $aggregation = new EnumeratedAggregationInfo(
                items: enumerateds.all(repository),
                type: type
        )
        storage.put(type, $aggregation)
    }
}
