package com.example.onlineshopapp_frontend.ui.authentication.data.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(val token: String)