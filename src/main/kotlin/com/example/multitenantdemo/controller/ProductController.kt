package com.example.multitenantdemo.controller

import com.example.multitenantdemo.domain.dto.request.CreateProductRequest
import com.example.multitenantdemo.domain.model.Product
import com.example.multitenantdemo.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getAll(): List<Product> {
        return productService.getAll()
    }

    @PostMapping
    fun create(@RequestBody request: CreateProductRequest): Product {
        return productService.create(request)
    }
}
