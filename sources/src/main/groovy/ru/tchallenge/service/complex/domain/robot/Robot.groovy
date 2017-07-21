package ru.tchallenge.service.complex.domain.robot

import groovy.transform.CompileStatic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.robot.role.RobotRole

@CompileStatic
@Entity
@Table(name = 'robot')
class Robot extends GenericOrdinalEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = 'account_id', nullable = false, unique = true, updatable = false)
    Account account

    @NotNull
    @Column(name = 'title', nullable = false)
    String title

    @Column(name = 'description')
    String description

    @ManyToMany
    @JoinTable(name = 'robot_role_to_robot_map', joinColumns = @JoinColumn(name = 'robot_id'))
    Collection<RobotRole> roles = []
}
