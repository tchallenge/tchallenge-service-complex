package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import org.springframework.beans.factory.annotation.Autowired

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent
import ru.tchallenge.service.complex.domain.robot.role.RobotRoleRepository

@CompileStatic
@PackageScope
@MapperComponent
class RobotMapperBean extends GenericMapperBean implements RobotMapper {

    @Autowired
    RobotRoleRepository roleRepository

    @Override
    Robot asEntity(Robot entity, RobotInvoice invoice) {
        def $result = entity ?: new Robot()
        $result.with {
            id = invoice.id as Long ?: id
            roles = invoice.roles ? enumerateds.some(roleRepository, invoice.roles) : roles
            it
        }
    }

    @Override
    RobotInfo asInfo(Robot entity) {
        new RobotInfo(
                roles: enumerateds.infos(entity.roles)
        )
    }
}
