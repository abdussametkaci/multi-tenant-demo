package com.example.multitenantdemo.service

import com.example.multitenantdemo.domain.dto.request.CreateProductRequest
import com.example.multitenantdemo.domain.model.Product
import com.example.multitenantdemo.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getAll(): List<Product> {
        return productRepository.findAll()
    }

    fun create(request: CreateProductRequest): Product {
        val product = Product(name = request.name, price = request.price)
        return productRepository.save(product)
    }
}
