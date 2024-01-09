package com.example.onlineshopapp_frontend.ui.authentication.data.services

import com.example.onlineshopapp_frontend.ui.authentication.data.Routes
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.product.CategoryDto
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.product.ProductDto
import com.example.onlineshopapp_frontend.ui.authentication.data.dto.user.UserData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import java.util.UUID

class ProductServiceImpl(private val client: HttpClient) : ProductService {
    override suspend fun getProduct(id: String): ProductDto? {

        return try {
            val response = client.get(Routes.GET_PRODUCT.plus(id)) {
                contentType(ContentType.Application.Json)
            }

            if (response.status.isSuccess()) {
                return response.body<ProductDto>() // Zwracanie AuthorizationResponse z tokenem
            } else {
                null // Jeśli rejestracja się nie powiodła
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getProducts(): List<ProductDto>? {
        return try {
            val response = client.get(Routes.PRODUCTS) {
                contentType(ContentType.Application.Json)
            }

            if (response.status.isSuccess()) {
                return response.body<List<ProductDto>>() // Zwracanie AuthorizationResponse z tokenem
            } else {
                null // Jeśli rejestracja się nie powiodła
            }
        } catch (e: Exception) {
            null
        }
    }
}