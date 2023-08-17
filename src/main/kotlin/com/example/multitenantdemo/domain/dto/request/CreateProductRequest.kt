package com.example.multitenantdemo.domain.dto.request

import java.math.BigDecimal

data class CreateProductRequest(
    val name: String,
    val price: BigDecimal
)
