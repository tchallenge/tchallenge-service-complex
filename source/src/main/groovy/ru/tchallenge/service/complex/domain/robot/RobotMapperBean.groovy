package ru.tchallenge.service.complex.domain.robot

import groovy.transform.PackageScope
import groovy.transform.TypeChecked

import ru.tchallenge.service.complex.common.GenericMapperBean
import ru.tchallenge.service.complex.convention.component.MapperComponent

@TypeChecked
@PackageScope
@MapperComponent
class RobotMapperBean extends GenericMapperBean implements RobotMapper {

    @Override
    RobotInfo asInfo(Robot entity) {
        new RobotInfo(
                roles: enumerateds.infos(entity.roles)
        )
    }
}
