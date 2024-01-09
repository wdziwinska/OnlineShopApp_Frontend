package com.example.onlineshopapp_frontend.ui.authentication.data.services

import com.example.onlineshopapp_frontend.ui.authentication.data.dto.product.ProductDto
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.UserData
import java.util.UUID

interface ProductService {
    suspend fun getProduct(id: String): ProductDto?
    suspend fun getProducts(): List<ProductDto>?

}