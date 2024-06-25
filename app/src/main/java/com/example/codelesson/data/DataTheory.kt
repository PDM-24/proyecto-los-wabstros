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
            "Estructura básica",
            "Cuando programas en C++, es necesario empezar usando la librería estándar iostream: #include <iostream>. Luego debes incluir el namespace principal: using namespace std; . Y declaras la función principal: \n\n" +
                    "int main (){\n" +
                    "\treturn 0;\n" +
                    "}\n" +
                    "\n" +
                    "El return 0 indica que la función retorna 0, todas las funciones deben retornar algo.\n"
        ),

        DataTheory(
            "Output",
            "Al combinar el objeto cout con el operador << es posible imprimir texto en pantalla. cout << “”Hola mundo”; Es importante siempre terminar con un ; para indicar el final de la línea"
        ),

        DataTheory(
            "Comentarios",
            "Cuando quieras comentar tu código es necesario que uses //aquí va el comentario o en su defecto /* aquí va tu comentario */ en caso de que quieras comentar varias líneas."
        ),

        DataTheory(
            "Tipos de variables",
            "Hay distintos tipos de variables, int (enteros), double (decimales), char(caracter), string(texto), bool (guarda true o false). Para declararlos se pone el tipo de variable y el nombre que prefieras: int n = 0, m = 1, s;  Puedes declarar varias variables en una línea con el uso de la coma e inicializarlas al instante usando el operador = ."
        ),

        DataTheory(
            "Inputs",
            "Para leer datos se hace uso de la variable predefinida llamada cin y del operador >>. Al usar cin >> se logra leer los datos ingresados por teclado, y luego se pueden guardar en una variable de esta manera: cin >> variable;"
        ),

        DataTheory(
            "Operadores",
            "Hay distintos tipos de operadores en C++. Están los aritmeticos: + suma, - resta, * multiplicación, / división, % módulo, ++ suma 1, – resta 1. Los cuales se pueden usar de esta manera:  int sum = n1 + n2; sum2 += 2; . También hay operadores de comparación como: == verifica si dos variables son iguales, != verifica si dos variables son distintas, > verifica si la primera variable es mayor a la segunda, < verifica si la primera variable es menor a la segunda. Otros operadores importantes son los lógicos: && es un y-lógico, || es un o-lógico"
        ),

        DataTheory(
            "Strings",
            "En C++ hay varias cosas que facilitan el uso de strings. En caso de querer concatenar algo al final de un string puedes usar myString = otroString.(“Hola”); . En caso de necesitar el número de caracteres que tiene tu string usa myString.length() o myString.size(). También es importante conocer algunos caracteres especiales como: \\n permite saltar de línea, \\t permite poner un espacio al inicio, \\\\ permite poner una \\"
        ),
        DataTheory(
            "Booleanos",
            "Al hacer una verificación con operadores de comparación o con operadores lógicos, se obtiene un valor booleano, ya sea true o false. En caso de que la comparación se cumpla se devuelve true, y en caso contrario false. 1 < 3 en este caso se devolvería true o un 1. 1 > 3 y en este caso se devolvería false o un 0."
        ),

        DataTheory(
            "Condiciones",
            "En C++ es posible ejecutar un código u otro dependiendo del resultado de una comparación. Esto se hace con el if(condición) el cual permite ejecutar un código en caso de que se cumpla la condición dentro del paréntesis. También, con un else, se puede ejecutar otro código en caso de que no se cumpla lo definido en el if. Un ejemplo sería:\n" +
                    "if(condicion){\n" +
                    "\tcout << “Hola”;\n" +
                    "}else {\n" +
                    "\tcout << “Adios”;\n" +
                    "}\n"
        )
    )
}