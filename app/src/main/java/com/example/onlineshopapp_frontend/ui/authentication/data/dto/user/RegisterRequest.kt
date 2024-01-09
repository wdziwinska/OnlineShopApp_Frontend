package com.example.onlineshopapp_frontend.ui.authentication.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val login: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)