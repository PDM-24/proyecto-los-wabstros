package com.example.codelesson.util

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelesson.data.models.ExpUpdateData
import com.example.codelesson.data.models.LessonData
import com.example.codelesson.data.models.TitleLesson
import com.example.codelesson.data.remote.api.ApiClient
import com.example.codelesson.model.LessonStatus
import com.example.codelesson.model.Practice
import com.example.codelesson.model.Question
import com.example.codelesson.ui.components.navigation.QuizGraph
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

    private val _practiceList = MutableStateFlow(
        Practice(
            theme = "Estructura básica",
            recap = "La librería esencial para usar input y output. Además del namespace std, que permite facilitar la escritura del código y como es necesario declarar la función principal.",
            lesson = "Cuando programas en C++, es necesario empezar usando la librería estándar iostream: #include <iostream>. Luego debes incluir el namespace principal: using namespace std; . Y declaras la función principal: \\n\n" +
                    "int main (){\n" +
                    "\t\treturn 0;\n" +
                    "}\n" +
                    "\n" +
                    "El return 0 indica que la función retorna 0, todas las funciones deben retornar algo.\n",
            questions = mutableListOf(
                Question(
                    incorrectAnswers = listOf(
                        "librería",
                        "namespace"
                    ),
                    code = "",
                    hint = "Dentro va un return 0",
                    type = 1,
                    question = "int main()",
                    correctAnswer = "función principal"
                ),
                Question(
                    incorrectAnswers = emptyList(),
                    code = "#include<$$>",
                    hint = "La librería lleva stream en el nombre",
                    type = 2,
                    question = "Declara la librería estándar",
                    correctAnswer = "iostream"
                ),
                Question(
                    incorrectAnswers = listOf(
                        "Declara una variable",
                        "Declara una librería"
                    ),
                    code = "return 0;",
                    hint = "Es una traducción literal",
                    type = 3,
                    question = "",
                    correctAnswer = "retorna 0"
                )
            )
        )
    )

    private val _completed = MutableStateFlow(false)
    val completed = _completed.asStateFlow()

    val practiceList = _practiceList.asStateFlow()

    private val _questionList = MutableStateFlow(_practiceList.value.questions.shuffled())
    val questionList = _questionList.asStateFlow()

    private val _nextNavigationRoute = MutableStateFlow("")
    val nextNavigationRoute = _nextNavigationRoute.asStateFlow()

    private val _index = MutableStateFlow(0)
    val index = _index.asStateFlow()

    private val _endIndicator = MutableStateFlow(0)
    val endIndicator = _endIndicator.asStateFlow()

    private val _lessonStatus = MutableStateFlow(
        listOf(
            LessonStatus("ESTRUCTURA BÁSICA", mutableStateOf(false)),
            LessonStatus("OUTPUT", mutableStateOf(false)),
            LessonStatus("COMENTARIOS", mutableStateOf(false)),
            LessonStatus("TIPOS DE VARIABLES", mutableStateOf(false)),
            LessonStatus("INPUTS", mutableStateOf(false)),
            LessonStatus("OPERADORES", mutableStateOf(false)),
            LessonStatus("STRINGS", mutableStateOf(false)),
            LessonStatus("BOOLEANOS", mutableStateOf(false)),
            LessonStatus("CONDICIONES", mutableStateOf(false)),
        )
    )
    val lessonStatus = _lessonStatus.asStateFlow()

    fun setLessonStatus(title: String, status: Boolean) {
        _lessonStatus.value.forEach {
            if (it.title == title && !it.complete.value) {
                it.complete.value = status
            }
        }
    }

    //When start the app, init var titleList
    init {
        getTitle()
    }

    fun setCompleted(status: Boolean) {
        _completed.value = status
    }

    fun checkLessonStatus(title: String) : Boolean{
        var status = false
         _lessonStatus.value.forEach {
             status = if (it.title == title) {
                 it.complete.value
             } else {
                 false
             }
        }

        return status
    }

    fun resetIndex(){
        _index.value = 0
        _endIndicator.value = 0
    }

    fun addIndex(){
        if(_questionList.value.size - 1 > _index.value){
            _index.value += 1
        }

        _endIndicator.value += 1
    }

    fun resetNavRoute() {
        _nextNavigationRoute.value = ""
    }

    fun verifyTypeOfQuestion(
        index: Int
    ) {
        when (_questionList.value[index].type) {
            1 -> _nextNavigationRoute.value = QuizGraph.MovingLabel.route
            2 -> _nextNavigationRoute.value = QuizGraph.ResponseEntry.route
            3 -> _nextNavigationRoute.value = QuizGraph.MultipleResponse.route
        }
    }

    private val _titleList = MutableStateFlow<List<TitleLesson>>(emptyList())
    val titleList = _titleList.asStateFlow()
    private val _getId = MutableStateFlow("")
    private val _getLesson = MutableStateFlow(
        LessonData(
            "", "", "", "", emptyList()
        )
    )
    val getLesson = _getLesson.asStateFlow()
    private val _token = MutableStateFlow("")
    val token = _token.asStateFlow()

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

    fun setId(id: String) {
        _getId.value = id
    }
    fun getLesson() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getLessonById(_getId.value)
                _getLesson.value = response.data
                val newQuestion = mutableListOf<Question>()
                var question: Question
                _getLesson.value.questions.forEach {
                    question = Question(it.type, it.code, it.question, it.hint, it.correctAnswer, it.options)
                    newQuestion.add(question)
                }
                val newLesson = Practice(
                    _getLesson.value.title,
                    _getLesson.value.lesson,
                    _getLesson.value.recap,
                    newQuestion
                )
                _practiceList.value = newLesson
                _questionList.value = _practiceList.value.questions.shuffled()
                _completed.value = true
            } catch (e: Exception) {
                Log.d("Get Lesson", e.message.toString())
            }
        }
    }
    //Funcion para obtener el token desde el UserViewModel
    fun getToken(token: String) {
        _token.value = token
    }

    fun obtainToken() : String {
        return _token.value
    }
}