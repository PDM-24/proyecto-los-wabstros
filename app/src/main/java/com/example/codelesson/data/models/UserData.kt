package com.example.codelesson.data.models

data class Profile(
    val message: String,
    val data: UserData
)

data class UserData(
    val name: String,
    val email: String,
    val lastName : String,
    val password: String,
    val exp: Int = 0
)