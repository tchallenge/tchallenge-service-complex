package ru.tchallenge.service.complex

import groovy.transform.CompileStatic

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@CompileStatic
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class PersistenceConfigurator {

    @Bean
    DataSource dataSourceHsql() {
        new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .generateUniqueName(true)
                .build()
    }

    @Bean
    HibernateJpaVendorAdapter vendorAdapterHsql() {
        new HibernateJpaVendorAdapter(
                database: Database.HSQL,
                generateDdl: true
        )
    }

    @Bean
    EntityManagerFactory entityManagerFactory(DataSource dataSource, HibernateJpaVendorAdapter adapter) {
        def factory = new LocalContainerEntityManagerFactoryBean(
                jpaVendorAdapter: adapter,
                packagesToScan: [Application.package.name] as String[],
                dataSource: dataSource
        )
        factory.afterPropertiesSet()
        factory.object
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        new JpaTransactionManager(
                entityManagerFactory: factory
        )
    }
}
