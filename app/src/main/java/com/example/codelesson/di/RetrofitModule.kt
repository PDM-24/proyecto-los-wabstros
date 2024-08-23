package com.example.codelesson.di

import com.example.codelesson.data.remote.api.ApiService
import com.example.codelesson.data.remote.api.RetrofitClient
import com.example.codelesson.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

//Indica que es un modulo de dagger
@Module
//Utiliza el patron de diseño singleton, lo que hace que la instancia de la dependencia
//dure tod el ciclo de la aplicacion
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}