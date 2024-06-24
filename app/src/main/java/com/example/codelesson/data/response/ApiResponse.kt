package com.example.codelesson.data.response

data class LoginResponse(
    val message: String,
    val data: TokenData
)

data class TokenData(
    val token: String
)