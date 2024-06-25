package com.example.codelesson.data.remote.api

import com.example.codelesson.data.models.DataLesson
import com.example.codelesson.data.models.ExpUpdateData
import com.example.codelesson.data.models.LessonResponse
import com.example.codelesson.data.models.PasswordData
import com.example.codelesson.data.models.Profile
import com.example.codelesson.data.models.UserData
import com.example.codelesson.data.models.UserDataLogin
import com.example.codelesson.data.models.UserDataUpdate
import com.example.codelesson.data.models.UserExpData
import com.example.codelesson.data.response.LoginResponse
import com.example.codelesson.util.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    //Makes the login request and returns a token which can be used to bring the user's data
    @POST(Constants.AUTH_CONSTANT + Constants.LOG_USER)
    suspend fun auth(
        @Body userDataLogin: UserDataLogin
    ) : LoginResponse

    @GET(Constants.USER_CONSTANT + Constants.GET_USER_PROFILE)
    suspend fun getUser(
        @Header("Authorization") token: String
    ) : Profile

    @POST(Constants.AUTH_CONSTANT + Constants.POST_USER)
    suspend fun postUser(@Body userData: UserData)

    @GET(Constants.LESSON_CONSTANT + Constants.GET_LESSON_TITLE)
    suspend fun getLessonTitle() : DataLesson

    @GET("${Constants.LESSON_CONSTANT}${Constants.GET_LESSON}")
    suspend fun getLessonById(@Query("id") id: String) : LessonResponse

    @POST(Constants.USER_CONSTANT + Constants.UPDATE_USER_PASSWORD)
    suspend fun updatePassword(
        @Body password: PasswordData,
        @Header("Authorization") token: String,
    ) : LoginResponse

    @POST(Constants.USER_CONSTANT + Constants.UPDATE_USER_PROFILE)
    suspend fun updateProfile(
        @Body userData: UserDataUpdate,
        @Header("Authorization") token: String
    ) : LoginResponse

    @GET(Constants.USER_CONSTANT + Constants.GET_USER_EXP)
    suspend fun getExp(
        @Header("Authorization") token: String
    ) : UserExpData

    @POST(Constants.USER_CONSTANT + Constants.UPDATE_USER_EXP)
    suspend fun updateExp(
        @Body exp: ExpUpdateData,
        @Header("Authorization") token: String
    ) : LoginResponse
}