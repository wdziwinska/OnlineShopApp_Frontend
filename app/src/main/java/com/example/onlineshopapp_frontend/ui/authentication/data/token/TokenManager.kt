package com.example.onlineshopapp_frontend.ui.authentication.data.token

import android.content.Context

class TokenManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("token_store", Context.MODE_PRIVATE)
    private val TOKEN_KEY = "token"

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }
}