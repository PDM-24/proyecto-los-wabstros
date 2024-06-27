package com.example.codelesson.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.codelesson.R
import com.example.codelesson.data.models.UserDataUpdate
import com.example.codelesson.ui.components.profileComponents.mainLetters
import com.example.codelesson.ui.components.profileComponents.message
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.util.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var nombreUsuario: String = ""
var apellidoUsuario: String = ""
var emailUsuario: String = ""

private val emptyFieldText: MutableState<Boolean> = mutableStateOf(false)

@Composable
fun EditProfile(
    innerPadding: PaddingValues,
    viewModel: UserViewModel,
    navController: NavHostController,
) {
    val state by viewModel.userData.collectAsState()
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .fillMaxSize()
            .background(DarkGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.default_image),
                contentDescription = "profile photo"
            )
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.BottomEnd)
                    .height(30.dp)
                    .width(30.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_circle),
                contentDescription = "add icon",
                tint = Color.White
            )
        }

        mainLetters(name = "Informacion personal")

        Spacer(modifier = Modifier.height(20.dp))

        profileTexField(state.name, "Nombre")

        profileTexField(state.lastName, "Apellido")

        profileTexField(state.email, "Correo electronico")

        Spacer(modifier = Modifier.height(75.dp))

        profilBbutton("Actualizar") {
            if (nombreUsuario != "" && apellidoUsuario != "" && emailUsuario != "") {
                lifeCycleScope.launch {
                    val data = UserDataUpdate(nombreUsuario, apellidoUsuario, emailUsuario)
                    viewModel.updateProfile(data, context)
                    message(context, "Datos actualizados")
                    viewModel.getUser()
                    delay(500)
                    navController.popBackStack()
                }
            } else {
                emptyFieldText.value = false
                message(context, "Complete todos los datos")
            }
        }
    }
}

@Preview
@Composable
fun EditProfilePreview() {
    EditProfile(
        innerPadding = PaddingValues(0.dp),
        viewModel = UserViewModel(),
        navController = NavHostController(LocalContext.current)
    )
}

@Composable
fun profileTexField(contenido: String, label: String) {
    var valor by remember {
        mutableStateOf(contenido)
    }
    if (label == "Nombre") {

        if (valor != "") {
            nombreUsuario = valor
        } else {
            nombreUsuario = ""
        }
    } else if (label == "Apellido") {
        if (valor != "") {
            apellidoUsuario = valor
        } else {
            apellidoUsuario = ""
        }
    } else if (label == "Correo electronico") {
        if (valor != "") {
            emailUsuario = valor
        } else {
            emailUsuario = ""
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        value = valor,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        label = { Text(text = label) },
        modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(15.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        onValueChange = { valor = it }
    )
}
