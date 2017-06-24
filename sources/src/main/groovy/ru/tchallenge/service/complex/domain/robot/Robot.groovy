package ru.tchallenge.service.complex.domain.robot

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.CompileStatic

import ru.tchallenge.service.complex.common.ordinal.GenericOrdinalEntity
import ru.tchallenge.service.complex.domain.account.Account
import ru.tchallenge.service.complex.domain.robot.role.RobotRole

@CompileStatic
@Entity
@Table(name = "robot")
class Robot extends GenericOrdinalEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account

    @Column(name = "title")
    String title

    @Column(name = "description")
    String description

    @ManyToMany
    @JoinTable(name = "robot_role_to_robot_map", joinColumns = @JoinColumn(name = "robot_id"))
    Collection<RobotRole> roles
}
