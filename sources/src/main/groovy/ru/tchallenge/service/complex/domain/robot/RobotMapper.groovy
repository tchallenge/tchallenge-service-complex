package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapper
import ru.tchallenge.service.complex.common.enumerated.EnumeratedHelper
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.robot.role.RobotRoleRepository

@CompileStatic
@MapperComponent
class RobotMapper extends GenericMapper {

    @Autowired
    protected RobotRoleRepository roleRepository

    Robot asEntity(Robot entity, RobotInvoice invoice) {
        entity = entity ?: new Robot()
        return entity.with {
            id = invoice.id as Long ?: id
            roles = invoice.roles ? EnumeratedHelper.many(roleRepository, invoice.roles) : roles
            it
        }
    }

    RobotInfo asInfo(Robot entity) {
        return new RobotInfo(
                roles: EnumeratedHelper.many(entity.roles)
        )
    }
}
