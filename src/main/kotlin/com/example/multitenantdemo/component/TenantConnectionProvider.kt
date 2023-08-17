package com.example.multitenantdemo.component

import org.hibernate.cfg.AvailableSettings
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.sql.Connection
import javax.sql.DataSource


@Component
class TenantConnectionProvider(private val datasource: DataSource) : MultiTenantConnectionProvider, HibernatePropertiesCustomizer {

    override fun isUnwrappableAs(unwrapType: Class<*>?): Boolean = false

    override fun <T : Any?> unwrap(unwrapType: Class<T>?): T? = null

    override fun getAnyConnection(): Connection = getConnection("public")

    override fun releaseAnyConnection(connection: Connection) = connection.close()

    override fun getConnection(tenantIdentifier: String): Connection {
        val connection = datasource.connection
        connection.schema = tenantIdentifier
        return connection
    }

    override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
        connection.schema = "public"
        releaseAnyConnection(connection)
    }

    override fun supportsAggressiveRelease(): Boolean = false

    override fun customize(hibernateProperties: MutableMap<String, Any>) {
        hibernateProperties[AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER] = this
    }
}
