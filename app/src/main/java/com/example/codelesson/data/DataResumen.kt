package com.example.codelesson.data

data class DataResumen(
    val title: String,
    val resumen: String
)

fun ResumenList(): List<DataResumen> {
    return listOf(
        DataResumen(
            "Estructura básica",
            "La librería esencial para usar input y output. Además del namespace std, que permite facilitar la escritura del código y como es necesario declarar la función principal"
        ),
        DataResumen(
            "Output",
            "Cómo imprimir imprimir texto en pantalla usando cout y <<."
        ),
        DataResumen(
            "Comentarios",
            "Cómo comentar una línea o varias líneas de código para documentar tú código adecuadamente."
        ),
        DataResumen(
            "Tipos de variables",
            "Los distintos tipos de variables en C++, tales como int, double, char, string, bool. Además de cómo declararlos e inicializarlos."
        ),
        DataResumen(
            "Inputs",
            "Cómo leer datos y guardarlos en la variable adecuada utilizando cin >>."
        ),
        DataResumen(
            "Operadores",
            "Los distintos tipos de operadores para realizar operaciones matemáticas o verificaciones lógicas.Los distintos tipos de operadores para realizar operaciones matemáticas o verificaciones lógicas."
        ),
        DataResumen(
            "Strings",
            "Algunas funciones que se usan en variables tipo string, además de algunos caracteres especiales para saltar línea y poner tabulación entre otras cosas."
        ),
        DataResumen(
            "Booleanos",
            "Los valores que guardan los booleanos: true, false, 1, 0."
        ),
        DataResumen(
            "Condiciones",
            "La utilización de los condicionales para ejecutar un código u otro dependiendo de las necesidades que se tengan en el código."
        )
    )
}