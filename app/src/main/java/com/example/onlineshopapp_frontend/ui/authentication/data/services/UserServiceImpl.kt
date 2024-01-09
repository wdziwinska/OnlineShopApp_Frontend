package com.example.onlineshopapp_frontend.ui.authentication.data.services

import com.example.onlineshopapp_frontend.ui.account.User
import com.example.onlineshopapp_frontend.ui.authentication.data.Routes
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.AuthorizationResponse
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.LoginRequest
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.RegisterRequest
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.UserData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class UserServiceImpl(
    private val client: HttpClient
) : UserService {

    override suspend fun getMe(token: String): UserData? {

        return try {
            val response = client.get(Routes.ABOUT_ME) {
                contentType(ContentType.Application.Json)
                header("Authorization", "Bearer $token")
            }

            if (response.status.isSuccess()) {
                return response.body<UserData>() // Zwracanie AuthorizationResponse z tokenem
            } else {
                null // Jeśli rejestracja się nie powiodła
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun registerUser(
        login: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): AuthorizationResponse? {
        return try {
            val response = client.post(Routes.REGISTER) {
                contentType(ContentType.Application.Json)
                setBody(RegisterRequest(login, firstName, lastName, email, password))
            }

            if (response.status.isSuccess()) {
                return response.body<AuthorizationResponse>() // Zwracanie AuthorizationResponse z tokenem
            } else {
                null // Jeśli rejestracja się nie powiodła
            }
        } catch (e: Exception) {
            null
        }
    }

    /*override suspend fun registerUser(login: String, firstName: String, lastName: String, email: String, password: String): AuthorizationResponse? {
        return try {

        } catch (e: RedirectResponseException) {
            null
        }
    }*/

    override suspend fun loginUser(login: String, password: String): AuthorizationResponse? {
        return try {
            println("CALLING LOGIN SERVICE")
            val response = client.post(Routes.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(login, password))
            }
            println("WAITING FOR LOGIN SERVICE")

            println(response.status)
            if (response.status.isSuccess()) {
                return response.body<AuthorizationResponse>();
            } else {
                null // Jeśli rejestracja się nie powiodła
            }
        } catch (e: Exception) {
            null
        }
    }
}