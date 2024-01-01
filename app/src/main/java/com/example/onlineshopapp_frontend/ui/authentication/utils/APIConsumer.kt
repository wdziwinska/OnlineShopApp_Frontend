package com.example.onlineshopapp_frontend.ui.authentication.utils

import com.example.onlineshopapp_frontend.ui.authentication.data.UniqueEmailValidationResponse
import com.example.onlineshopapp_frontend.ui.authentication.data.ValidateEmailBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIConsumer {

    @POST("/users/validate-unique-email")
    fun validateEmailAddress(@Body body: ValidateEmailBody) : Response<UniqueEmailValidationResponse>

}