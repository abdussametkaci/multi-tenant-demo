package com.example.multitenantdemo.configuration.filter

import com.example.multitenantdemo.client.MockTenantAPI
import com.example.multitenantdemo.client.NotFoundException
import com.example.multitenantdemo.client.dto.reponse.toTenant
import com.example.multitenantdemo.component.TenantContext
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

// Filter by subdomain
@Component
class TenantFilter(private val mockTenantAPI: MockTenantAPI) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val tenantName = getTenantName(request)
        val tenant = tenantName?.let { mockTenantAPI.getByName(it) } ?: throw NotFoundException()
        TenantContext.CURRENT_TENANT = tenant.toTenant()

        filterChain.doFilter(request, response)
    }

    private fun getTenantName(request: HttpServletRequest): String? {
        val domain = request.serverName
        val dotIndex = domain.indexOf(".")
        var tenant: String? = null
        if (dotIndex != -1) {
            tenant = domain.substring(0, dotIndex)
        }
        return tenant
    }
}

// Filter by header
/*
@Component
class TenantFilter(private val mockTenantAPI: MockTenantAPI) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val tenantId = request.getHeader("tenantId")
        val tenant = mockTenantAPI.getByTenantId(UUID.fromString(tenantId))
        TenantContext.CURRENT_TENANT = tenant.toTenant()

        filterChain.doFilter(request, response)
    }
}
*/
