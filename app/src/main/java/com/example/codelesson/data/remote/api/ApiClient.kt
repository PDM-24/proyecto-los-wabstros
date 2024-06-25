package com.example.codelesson.data.remote.api

import com.example.codelesson.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val ApiClient : ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}