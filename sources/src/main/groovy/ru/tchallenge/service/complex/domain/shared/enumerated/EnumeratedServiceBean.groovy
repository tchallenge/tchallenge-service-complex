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
    // TODO: change this service to some storage component

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

    private final Map<String, EnumeratedAggregationInfo> aggregations = [:]

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

    @Override
    protected void init() {
        super.init()
        store('account.realm', accountRealmRepository)
        store('account.status', accountStatusRepository)
        store('account.verification', accountVerificationRepository)
        store('assignment.status', assignmentStatusRepository)
        store('employee.role', employeeRoleRepository)
        store('event.category', eventCategoryRepository)
        store('event.status', eventStatusRepository)
        store('maturity', maturityRepository)
        store('robot.role', robotRoleRepository)
        store('specialization', specializationRepository)
        store('task.category', taskCategoryRepository)
        store('task.difficulty', taskDifficultyRepository)
        store('task.snippet.style', taskSnippetStyleRepository)
        store('task.status', taskStatusRepository)
        store('workbook.status', workbookStatusRepository)
    }

    private void store(String type, GenericEnumeratedRepository repository) {
        def $aggregation = new EnumeratedAggregationInfo(
                items: enumerateds.all(repository),
                type: type
        )
        aggregations.put(type, $aggregation)
    }
}
