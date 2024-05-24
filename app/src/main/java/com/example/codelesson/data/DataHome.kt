package com.example.codelesson.data

data class DataHome(
    val name: String
)

fun getName(): List<DataHome> {
    return listOf(
        DataHome(
            "GET STARTED"
        ),
        DataHome(
            "SINTAXIS"
        ),
        DataHome(
            "SALIDA DE DATOS"
        ),
        DataHome(
            "COMENTARIOS"
        ),
        DataHome(
            "VARIABLES"
        ),
        DataHome(
            "ENTRADA DE DATOS"
        ),
        DataHome(
            "TIPOS DE DATOS"
        ),
        DataHome(
            "OPERADORES"
        )
    )
}