package com.example.codelesson.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.navigation.ProfileGraph
import com.example.codelesson.ui.components.profileComponents.mainLetters
import com.example.codelesson.ui.components.profileComponents.message
import com.example.codelesson.util.KeyboardFunctions
import com.example.codelesson.util.UserViewModel

var userPassword = ""
var newPassword = ""
var confirmPassword = ""

private val erasePassword: MutableState<Boolean> = mutableStateOf(false)

@Composable
fun Security(innerPadding: PaddingValues, viewModel: UserViewModel, navController: NavHostController) {
    val focusManager = LocalFocusManager.current
    val focused = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .clickable {

            }
    ) {
        mainLetters("Seguridad")

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            securityTextFields("Contraseña actual")

            securityTextFields("Contraseña nueva")

            securityTextFields("Confirmar contraseña nueva")
        }

        Row (
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            val context = LocalContext.current

            profilBbutton("ACTUALIZAR") {
                if (checkedEmptyPassword(newPassword) && checkedEmptyPassword(confirmPassword) && checkedEmptyPassword(userPassword)){
                    if (checkedNewPassword(newPassword, confirmPassword)){
//                        var userPassword = PasswordData(userPassword, newPassword)
//                        viewModel.updatepassword(userPassword)
                        message(context, mensaje = "Contraseña actualizada")
                        navController.navigate(ProfileGraph.Profile.route)
                    }else{
                        message(context, mensaje = "Las contraseñas no coinciden")
                        erasePassword.value = true
                        KeyboardFunctions.ClearFocus(focusManager, focused)
                    }
                }else{
                    message(context, mensaje = "Ingrese todos los datos")
                    erasePassword.value = true
                    KeyboardFunctions.ClearFocus(focusManager, focused)
                    Log.d("TAG", "Algun campo esta vacio")
                }
            }
        }
    }
}

@Preview
@Composable
fun SecurityPreview() {
    Security(PaddingValues(0.dp), UserViewModel(), NavHostController(LocalContext.current))
}

@Composable
fun securityTextFields(label : String, ) {
    var password by rememberSaveable { mutableStateOf("") }

    if (label == "Contraseña actual"){
        userPassword = password
    }else if (label == "Contraseña nueva"){
        if (!erasePassword.value){
            newPassword = password
        }else{
            newPassword = ""
            password = ""
        }

    }else if (label == "Confirmar contraseña nueva"){
        if (!erasePassword.value){
            confirmPassword = password
        }else{
            erasePassword.value = false
            confirmPassword = ""
            password = ""
        }
    }

    Spacer(modifier = androidx.compose.ui.Modifier.height(25.dp))

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(label) },
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
        modifier = Modifier
            .width(300.dp)
            .clip(RoundedCornerShape(15.dp)),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
    )
}

fun checkedNewPassword(newPassword: String, confirmPassword: String): Boolean {
    return newPassword == confirmPassword
}

fun checkedEmptyPassword(password: String): Boolean {
    return (password != "")
}
