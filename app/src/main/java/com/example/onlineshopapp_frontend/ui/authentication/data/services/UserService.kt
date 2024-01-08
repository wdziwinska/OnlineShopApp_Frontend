package com.example.onlineshopapp_frontend.ui.authentication.data.services

import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.AuthorizationResponse
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.UserData
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.*

interface UserService {
    suspend fun getMe(token: String): UserData?
    suspend fun registerUser(login: String, firstName: String, lastName: String, email: String, password: String): AuthorizationResponse?
    suspend fun loginUser(login: String, password: String): AuthorizationResponse?

    companion object {
        fun create(): UserService {
            val client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json() // Użyj odpowiedniego serializera JSON
                }
            }

            return UserServiceImpl(client)
        }
    }
}