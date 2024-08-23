package com.example.codelesson.ui.login

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
import com.example.codelesson.R
import com.example.codelesson.data.models.UserDataLogin
import com.example.codelesson.ui.components.logincomponents.PasswordField
import com.example.codelesson.ui.components.logincomponents.UserTextField
import com.example.codelesson.ui.components.navigation.Graph
import com.example.codelesson.ui.components.navigation.LoginGraph
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.PracticeViewModel
import com.example.codelesson.util.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(
    userViewModel: UserViewModel,
    practiceViewModel: PracticeViewModel,
    navController: NavHostController
) {
    //val viewModel = remember { UserViewModel() }
    val context = LocalContext.current

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val token by userViewModel.token.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val error by userViewModel.error.collectAsState()

    LaunchedEffect(error) {
        if (error) {
            isLoading = false
            userViewModel.setError()
        }
    }

    LaunchedEffect(token) {
        if(token.isNotEmpty()){
            practiceViewModel.getToken(token)
            //context.startActivity(Intent(context, MainActivity::class.java))
            navController.navigate(Graph.HOME.graph)
        } else {
            isLoading = false
        }
    }

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
                    value = login,
                    onChange = { login = it }
                )

                PasswordField(
                    value = password,
                    onChange = { password = it },
                    submit = {
                        if (login.isNotEmpty() && password.isNotEmpty()) {
                            userViewModel.login(UserDataLogin(login, password), context)
                        } else {
                            Toast.makeText(context, "Por favor completar la informacion solicitada", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        if (login.isNotEmpty() && password.isNotEmpty() ) {
                            isLoading = true
                            userViewModel.login(UserDataLogin(login, password), context)
                        } else {
                            Toast.makeText(context, "Por favor completar la informacion solicitada", Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = login.isNotEmpty() && password.isNotEmpty(), //&& !isLoading,
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF572970)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp)
                        )
                    } else {
                        Text(stringResource(id = R.string.login_btn_login),
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