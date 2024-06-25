package com.example.codelesson.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelesson.data.models.UserData
import com.example.codelesson.data.models.UserDataLogin
import com.example.codelesson.data.remote.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    //Allows access to the functions that make requests to the API
    private val api = ApiClient.ApiClient

    private val _token = MutableStateFlow("")
    val token = _token.asStateFlow()
    private val _userData = MutableStateFlow(UserData("", "", "", "",))
    val userData = _userData.asStateFlow()

    fun login(data: UserDataLogin, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.auth(data)
                _token.value = response.data.token
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "User not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            try {
                //Send token for get the user data from api
                val response = api.getUser("Bearer " + _token.value)
                _userData.value = response.data
            }
            catch (e: Exception) {
                Log.d("Get User", e.message.toString())
            }
        }
    }

    fun createUser(userData: UserData, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                api.postUser(userData)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context, "The user was created successfully!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context, "There was an error creating the user!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}