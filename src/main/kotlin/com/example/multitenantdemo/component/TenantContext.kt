package com.example.multitenantdemo.component

import java.util.UUID

object TenantContext {
    private val currentTenant = ThreadLocal<Tenant>().apply {
        set(Tenant(UUID.fromString("00000000-0000-0000-0000-000000000000"), "public"))
    }

    var CURRENT_TENANT: Tenant
        get() = currentTenant.get()
        set(tenant) {
            currentTenant.set(tenant)
        }
}
