package com.example.multitenantdemo.repository

import com.example.multitenantdemo.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository : JpaRepository<Product, UUID>
