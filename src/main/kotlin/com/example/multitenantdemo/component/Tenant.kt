package com.example.multitenantdemo.component

import java.util.UUID

data class Tenant(
    val id: UUID,
    val name: String
)
