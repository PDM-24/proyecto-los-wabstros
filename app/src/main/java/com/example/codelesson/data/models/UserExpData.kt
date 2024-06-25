package com.example.codelesson.data.models

data class UserExpData(
    val message: String,
    val data: ExpData
)

data class ExpData(
    val email: String,
    val exp: Int
)