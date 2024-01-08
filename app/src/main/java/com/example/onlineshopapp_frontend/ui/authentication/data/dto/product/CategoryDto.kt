package com.example.onlineshopapp_frontend.ui.authentication.data.dto.product

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto (
    val category: String,
    val categoryImgUrl: String
)