package com.example.onlineshopapp_frontend.ui.authentication.data

object Routes {
    const val JWT_TOKEN_KEY = "";

    private const val BASE_URL = "https://tim-ecommerce-65ead7726cf0.herokuapp.com";
    private const val API_VERSION = "/api/v1";

    const val REGISTER = "$BASE_URL$API_VERSION/auth/register"
    const val LOGIN = "$BASE_URL$API_VERSION/auth/login"
    const val ABOUT_ME = "$BASE_URL$API_VERSION/user/me"

    const val PRODUCTS = "$BASE_URL$API_VERSION/product/products"
    const val GET_PRODUCT = "$BASE_URL$API_VERSION/product/"
    const val CATEGORIES = "$BASE_URL$API_VERSION/product/categories"
    const val ADD_PRODUCT = "$BASE_URL$API_VERSION/product/add"


}