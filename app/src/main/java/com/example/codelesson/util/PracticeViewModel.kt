package com.example.codelesson.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codelesson.model.Practice
import com.example.codelesson.model.Question
import com.example.codelesson.ui.components.navigation.QuizGraph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PracticeViewModel : ViewModel() {
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
            questions = listOf(
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

    val practiceList = _practiceList.asStateFlow()

    private val _questionList = MutableStateFlow(_practiceList.value.questions.shuffled())
    val questionList = _questionList.asStateFlow()

    private val _nextNavigationRoute = MutableStateFlow("")
    val nextNavigationRoute = _nextNavigationRoute.asStateFlow()

    private val _index = MutableStateFlow(0)
    val index = _index.asStateFlow()

    fun resetIndex(){
        _index.value = 0
    }

    fun addIndex(){
        if(_questionList.value.size - 1 > _index.value)
            _index.value += 1
    }

    fun resetNavRoute() {
        _nextNavigationRoute.value = ""
    }

    fun verifyTypeOfQuestion(
        index: Int
    ){
        when(_questionList.value[index].type){
            1 -> _nextNavigationRoute.value = QuizGraph.MovingLabel.route
            2 -> _nextNavigationRoute.value = QuizGraph.ResponseEntry.route
            3 -> _nextNavigationRoute.value = QuizGraph.MultipleResponse.route
        }
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
}