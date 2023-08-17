package com.example.multitenantdemo.client.dto.reponse

import com.example.multitenantdemo.component.Tenant
import java.util.UUID

data class TenantResponse(
    val id: UUID,
    val name: String
)

fun TenantResponse.toTenant() = Tenant(id, name)
