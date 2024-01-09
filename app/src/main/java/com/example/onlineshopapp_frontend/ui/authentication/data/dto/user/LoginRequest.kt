package com.example.onlineshopapp_frontend.ui.authentication.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest (
    val login: String,
    val password: String
    )