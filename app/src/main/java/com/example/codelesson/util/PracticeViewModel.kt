package com.example.codelesson.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelesson.data.models.ExpUpdateData
import com.example.codelesson.data.models.LessonData
import com.example.codelesson.data.models.TitleLesson
import com.example.codelesson.data.remote.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PracticeViewModel : ViewModel() {
    private val api = ApiClient.ApiClient

    //Para establecer el titulo de la topbar del scaffold
    private val _titleTopBar = MutableStateFlow("Desarrollador C++")
    val titleTopBar = _titleTopBar.asStateFlow()

    //Para checar que una label esta siendo arrastrado
    private val _isCurrentlyDragging = MutableLiveData(false)
    private var isCurrentlyDragging = _isCurrentlyDragging

    private val _titleList = MutableStateFlow<List<TitleLesson>>(emptyList())
    val titleList = _titleList.asStateFlow()
    private val _getId = MutableStateFlow("")
    val getId = _getId.asStateFlow()
    private val _getLesson = MutableStateFlow(
        LessonData(
            "", "", "", "", emptyList()
        )
    )
    val getLesson = _getLesson.asStateFlow()
    private val _token = MutableStateFlow("")
    val token = _token.asStateFlow()
    private val _exp = MutableStateFlow(0)
    val exp = _exp.asStateFlow()

    //When start the app, init var titleList
    init {
        getTitle()
    }

    fun startDragging() {
        _isCurrentlyDragging.value = true
    }

    fun stopDragging() {
        isCurrentlyDragging.value = false
    }

    fun setTitle(title: String) {
        _titleTopBar.value = title
    }

    fun VerifyingAnswer(answer: String, correctAnswer: String) =
        answer == correctAnswer

    private fun getTitle() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getLessonTitle()
                _titleList.value = response.data
            } catch (e: Exception) {
                Log.d("Get Title", e.message.toString())
            }
        }
    }

    fun getLesson() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getLessonById(_getId.value)
                _getLesson.value = response.data
            } catch (e: Exception) {
                Log.d("Get Lesson", e.message.toString())
            }
        }
    }
    //Funcion para obtener el token desde el UserViewModel
    fun getToken(token: String) {
        _token.value = token
    }

    fun getExp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getExp("Bearer ${_token.value}")
                _exp.value = response.data.exp
                Log.d("Exp", _exp.value.toString())
            }
            catch (e: Exception) {
                Log.d("Exp", e.message.toString())
            }
        }
    }
    //El parametro sera la nueva exp, la cual se calculara en la screen de congrats
    //y se le enviara como parametro
    fun updateExp(newExp: ExpUpdateData) {
        viewModelScope.launch {
            try {
                val response = api.updateExp(newExp, "Bearer ${_token.value}")
                _token.value = response.data.token
            }
            catch (e: Exception) {
                Log.d("Update Exp", e.message.toString())
            }
        }
    }
}