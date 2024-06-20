package com.example.codelesson.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.codelesson.data.LoginData
import com.example.codelesson.ui.components.navigation.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel : ViewModel() {
    var loginData by mutableStateOf(LoginData())

    private val _loginState = MutableStateFlow<LoginButtonState>(LoginButtonState.Ready)
    val loginState: StateFlow<LoginButtonState> = _loginState

    fun checkLogin(navController: NavHostController, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loginState.value = LoginButtonState.Loading
                delay(2000) // Simulando la obtención de datos de inicio de sesión
                if (checkCredentials(loginData)) {
                    withContext(Dispatchers.Main) {
                        _loginState.value = LoginButtonState.Success
                        navController.navigate(Graph.HOME.graph) {
                            popUpTo(Graph.LOGIN.graph) { inclusive = true }
                        }
                    }
                } else {
                    _loginState.value = LoginButtonState.Error(1)
                    loginData = LoginData()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                        _loginState.value = LoginButtonState.Ready
                    }
                }
            } catch (e: Exception) {
                _loginState.value = LoginButtonState.Error(2)
            }
        }
    }

    private fun checkCredentials(loginData: LoginData): Boolean {
        return loginData.isNotEmpty() && loginData.login == "admin" // Simplificado para demostración
    }
}

sealed class LoginButtonState {
    object Ready : LoginButtonState()
    object Success : LoginButtonState()
    data class Error(val errorCode: Int) : LoginButtonState()
    object Loading : LoginButtonState()
}
