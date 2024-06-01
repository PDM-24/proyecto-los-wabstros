package com.example.codelesson.data

data class DataTheory(
    val title: String,
    val lesson: String,
)

data class DataQuiz(
    val type: Int,
    val code: String?,
    val question: String,
    val correctAnswer: String,
    val wrongAnswer: List<String>
)

fun theoryList(): List<DataTheory> {
    return listOf(
        DataTheory(
            "Syntax",
            "Cuando programas en C++, es necesario empezar usando la librería estándar iostream: #include <iostream>. Luego debes incluir el namespace principal: using namespace std; . Y declaras la función principal: \n\n" +
                    "int main (){\n" +
                    "\treturn 0;\n" +
                    "}\n" +
                    "\n" +
                    "El return 0 indica que la función retorna 0, todas las funciones deben retornar algo.\n"
        ),

        DataTheory(
            "Comentarios",
            "Cuando quieras comentar tu código es necesario que uses //aquí va el comentario o en su defecto /* aquí va tu comentario */ en caso de que quieras comentar varias líneas."
        ),

        DataTheory(
            "Variables",
            "Hay distintos tipos de variables, int (enteros), double (decimales), char(caracter), string(texto), bool (guarda true o false). Para declararlos se pone el tipo de variable y el nombre que prefieras: int n = 0, m = 1, s;  Puedes declarar varias variables en una línea con el uso de la coma e inicializarlas al instante usando el operador = ."
        ),

        DataTheory(
            "Entrada de datos",
            "Para leer datos se hace uso de la variable predefinida llamada cin y del operador >>. Al usar cin >> se logra leer los datos ingresados por teclado, y luego se pueden guardar en una variable de esta manera: cin >> variable;"
        )
    )
}