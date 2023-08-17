package com.example.multitenantdemo.client

import com.example.multitenantdemo.client.dto.reponse.TenantResponse
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MockTenantAPI {

    fun getAll(): List<TenantResponse> = TENANTS

    fun getByTenantId(tenantId: UUID): TenantResponse {
        return TENANTS.associateBy { it.id }[tenantId] ?: throw NotFoundException()
    }

    fun getByName(name: String): TenantResponse {
        return TENANTS.associateBy { it.name }[name] ?: throw NotFoundException()
    }

    private companion object {
        val TENANTS = listOf(
            TenantResponse(UUID.fromString("57e5624e-7453-42de-b4e2-645a385bdbae"), "apple"),
            TenantResponse(UUID.fromString("67e5624e-7453-42de-b4e2-645a385bdbae"), "samsung")
        )
    }
}

class NotFoundException : Exception()