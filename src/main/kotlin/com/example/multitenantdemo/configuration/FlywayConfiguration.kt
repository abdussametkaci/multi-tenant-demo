package com.example.multitenantdemo.configuration

import com.example.multitenantdemo.client.MockTenantAPI
import com.example.multitenantdemo.configuration.properties.DatabaseProperties
import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfiguration(
    private val databaseProperties: DatabaseProperties,
    private val mockTenantAPI: MockTenantAPI
) {

    @PostConstruct
    fun flyway() {
        val tenants = mockTenantAPI.getAll()

        tenants.forEach { tenant ->
            migrate(tenant.name)
        }
    }

    private fun migrate(schema: String) {
        val flyway = with(databaseProperties) {
            Flyway.configure()
                .baselineOnMigrate(true)
                .outOfOrder(true)
                .dataSource(url, username, password)
                .schemas(schema)
                .load()
        }

        flyway.migrate()
    }
}
