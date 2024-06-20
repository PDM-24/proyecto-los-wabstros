package com.example.codelesson.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codelesson.R
import com.example.codelesson.ui.components.logincomponents.SignupTextField
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins

@Composable
fun CreateAccount(
    navController: NavHostController
) {
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


                SignupTextField(hint = "Nombre", value = "" )

                SignupTextField(hint = "Apellido", value = "" )

                SignupTextField(hint = "Correo electrónico", value = "" )

                SignupTextField(hint = "Contraseña", value = "" )

                SignupTextField(hint = "Confirmar contraseña", value = "" )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.navigate("login") },
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