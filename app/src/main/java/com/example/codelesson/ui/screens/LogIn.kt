package com.example.codelesson.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codelesson.MainActivity
import com.example.codelesson.R
import com.example.codelesson.data.LoginData
import com.example.codelesson.ui.components.logincomponents.PasswordField
import com.example.codelesson.ui.components.logincomponents.UserTextField
import com.example.codelesson.ui.components.navigation.LoginGraph
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.LoginButtonState
import com.example.codelesson.util.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(
    navController: NavHostController
) {
    val viewModel = remember { UserViewModel() }
    val loginButtonState = viewModel.loginState.collectAsState()
    val context = LocalContext.current

    Surface(
        color = Color(0xFF1E1E1E),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logocodelesson),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 60.dp, top = 50.dp)
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Inicia sesión",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                UserTextField(
                    value = viewModel.loginData.login,
                    onChange = { data -> viewModel.loginData = viewModel.loginData.copy(login = data) }
                )

                PasswordField(
                    value = viewModel.loginData.pwd,
                    onChange = { data -> viewModel.loginData = viewModel.loginData.copy(pwd = data) },
                    submit = {
                        if (!checkCredentials(viewModel.loginData, context)) viewModel.loginData = LoginData()
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        viewModel.checkLogin(navController, context)
                    },
                    enabled = (viewModel.loginData.isNotEmpty() && (loginButtonState.value == LoginButtonState.Ready)),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF572970)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    when (loginButtonState.value) {
                        LoginButtonState.Loading ->
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp)
                            )
                        else -> Text(stringResource(id = R.string.login_btn_login),
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontFamily = audioWide,
                                fontWeight = FontWeight(500),
                                color = Color.White
                            ))
                    }

                }

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "¿Todavía no tienes una cuenta?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        color = Color(0xFF717171)
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Button(
                    onClick = { navController.navigate(LoginGraph.SignOn.route) },
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(0xFF717171),
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E1E1E)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        text = "CREAR UNA CUENTA",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = audioWide,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF717171)
                        )
                    )
                }
            }


        }
    }
}

fun checkCredentials(loginData: LoginData, context: Context): Boolean {
    return if (loginData.isNotEmpty() && loginData.login == "admin") {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
        true
    } else {
        Toast.makeText(context, context.resources.getString(R.string.login_toast_error), Toast.LENGTH_SHORT).show()
        false
    }

}