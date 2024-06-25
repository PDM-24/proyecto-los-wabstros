package com.example.codelesson.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.codelesson.R
import com.example.codelesson.data.models.UserData
import com.example.codelesson.ui.components.logincomponents.SignupTextField
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.UserViewModel

@Composable
fun CreateAccount(
    userViewModel: UserViewModel,
    navController: NavHostController
) {
    val name = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val context = LocalContext.current

    Surface(
        color = Color(0xFF1E1E1E),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier =  Modifier.fillMaxSize()){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {

                Image(painter = painterResource(id = R.drawable.logocodelesson),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 15.dp )
                        .height(75.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(text = "Crear Cuenta",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = audioWide,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp)
                )

                /*
                                SignupTextField(hint = "Nombre", value = "" )

                                SignupTextField(hint = "Apellido", value = "" )

                                SignupTextField(hint = "Correo electrónico", value = "" )

                                SignupTextField(hint = "Contraseña", value = "" )

                                SignupTextField(hint = "Confirmar contraseña", value = "" )
                                */

                SignupTextField(hint = "Nombre", value = name.value, onValueChange = { name.value = it })
                SignupTextField(hint = "Apellido", value = lastName.value, onValueChange = { lastName.value = it })
                SignupTextField(hint = "Correo electrónico", value = email.value, onValueChange = { email.value = it })
                SignupTextField(hint = "Contraseña", value = password.value, onValueChange = { password.value = it })
                SignupTextField(hint = "Confirmar contraseña", value = confirmPassword.value, onValueChange = { confirmPassword.value = it })

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    //onClick = { navController.navigate("login") },
                    onClick = {
                        if (password.value == confirmPassword.value) {
                            val userData = UserData(
                                name = name.value,
                                lastName = lastName.value,
                                email = email.value,
                                password = password.value,
                                exp = 0
                            )
                            userViewModel.createUser(userData, context)
                            navController.navigate("login")
                            Toast.makeText(context, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, context.resources.getString(R.string.signup_toast_password), Toast.LENGTH_SHORT).show()
                        }
                    },

                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF572970)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(start = 4.dp, end = 10.dp)
                ) {
                    Text(
                        text = "CREAR CUENTA",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = audioWide,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        )
                    )

                }

                Row(
                    modifier = Modifier.padding(top= 35.dp, bottom = 52.dp)
                ){
                    Text("¿Ya tienes una cuenta?   ",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            color = Color(0xFF717171)
                        )
                    )

                    Text("Iniciar Sesión",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = audioWide,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight(800),
                            color = Color(0xFFB6B6B6)
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )
                }
            }
        }
    }
}