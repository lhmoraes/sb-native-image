/*
 * This file is property of the Parlacom Telecommunications Brazil
 * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
 * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
 * and will be prosecuted to the maximum extent possible under the law.
 */
package net.parlacom.iot.sigfox.repository.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import net.parlacom.iot.sigfox.repository.config.RepositoryConstant.DEVICE_CONNECTION_FACTORY
import net.parlacom.iot.sigfox.repository.config.RepositoryConstant.DEVICE_ENTITY_TEMPLATE
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.dialect.MySqlDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.r2dbc.core.DatabaseClient
import javax.annotation.PostConstruct

/**
 *
 *
 * @author Luiz Moraes
 * @version 1.0, 2018-10-24
 */
@Configuration
@EnableR2dbcRepositories(entityOperationsRef = DEVICE_ENTITY_TEMPLATE,
                         basePackages = ["net.parlacom.iot.sigfox.repository.device"])
class DeviceRepositoryConfig {

    @Value("\${sigfox.datasource.device.r2dbc}")
    lateinit var r2dbcUrl: String

    /**
     * TODO
     *
     * @return
     */
    @Bean
    @Qualifier(DEVICE_CONNECTION_FACTORY)
    fun deviceConnectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(r2dbcUrl)
    }

    /**
     * TODO
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    fun deviceEntityTemplate(@Qualifier(DEVICE_CONNECTION_FACTORY) connectionFactory: ConnectionFactory): R2dbcEntityOperations {
        val strategy = DefaultReactiveDataAccessStrategy(MySqlDialect.INSTANCE)
        val databaseClient: DatabaseClient = DatabaseClient.builder()
                                                           .connectionFactory(connectionFactory)
                                                           .bindMarkers(MySqlDialect.INSTANCE.bindMarkersFactory)
                                                           .build()

        return R2dbcEntityTemplate(databaseClient, strategy)
    }

    /**
     * TODO
     *
     * val databasePopulator = ResourceDatabasePopulator()
     * ===> databasePopulator.addScripts(ClassPathResource("scripts/customers/schema.sql"), ClassPathResource("scripts/customers/data.sql"))
     * databasePopulator.populate(deviceConnectionFactory()).subscribe()
     */
    @PostConstruct
    fun initialize() {
        val databasePopulator = ResourceDatabasePopulator()
        databasePopulator.populate(deviceConnectionFactory()).subscribe()
    }

}