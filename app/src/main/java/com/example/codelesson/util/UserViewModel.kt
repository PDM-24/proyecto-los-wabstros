package com.example.codelesson.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelesson.data.models.ExpUpdateData
import com.example.codelesson.data.models.PasswordData
import com.example.codelesson.data.models.UserData
import com.example.codelesson.data.models.UserDataLogin
import com.example.codelesson.data.models.UserDataUpdate
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
    private val _userData = MutableStateFlow(UserData("", "", "", "",0))
    val userData = _userData.asStateFlow()
    private val _error = MutableStateFlow(_token.value.isEmpty())
    val error = _error.asStateFlow()
    private val _exp = MutableStateFlow(0)
    val exp = _exp.asStateFlow()
    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()
    private val _completeExp = MutableStateFlow(false)
    val completeExp = _completeExp.asStateFlow()

    fun login(data: UserDataLogin, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.auth(data)
                _token.value = response.data.token
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                }
                _error.value = false
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "User not found!", Toast.LENGTH_SHORT).show()
                }
                _error.value = true
            }
        }
    }

    fun setError() {
        _error.value = false
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

    fun updateExp(newExp: ExpUpdateData) {
        viewModelScope.launch {
            try {
                val response = api.updateExp(newExp, "Bearer ${_token.value}")
                _token.value = response.data.token
            }
            catch (e: Exception) {
                Log.d("Update Exp", e.message.toString())
            }
            _state.value = true
            getUser()
        }
    }

    fun setCompleteExp(condition: Boolean) {
        _completeExp.value = false
    }

    fun getExp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getExp("Bearer ${_token.value}")
                _exp.value = response.data.exp
            }
            catch (e: Exception) {
                Log.d("Exp", e.message.toString())
            }
            _completeExp.value = true
        }
    }

    fun updatePassword(password: PasswordData, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.updatePassword(password, "Bearer ${_token.value}")
                _token.value = response.data.token
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,"Password was changed successfully", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,"There was an error changing the password!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun deleteToke() {
        _token.value = ""
    }

    fun setStatusLesson(condition: Boolean) {
        _state.value = condition
    }
    fun updateProfile(userData: UserDataUpdate, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.updateProfile(userData, "Bearer ${_token.value}")
                _token.value = response.data.token
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,"Data was updated successfully", Toast.LENGTH_SHORT
                    ).show()
                }
                _completeExp.value = true
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,"There was an error updating the data", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}