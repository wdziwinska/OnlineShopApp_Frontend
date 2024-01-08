package com.example.onlineshopapp_frontend.ui.authentication.data.dto.product

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ProductDto(
    val uuid: String,
    val name: String,
    val description: String,
    val price: Long,
    val imageUrl: String,
    val category: CategoryDto,
    val quantityInStock: Long
)