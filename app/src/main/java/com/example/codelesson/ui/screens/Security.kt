package com.example.codelesson.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.ui.components.profileComponents.mainLetters
import com.example.codelesson.ui.components.profileComponents.mainLettersPreview
import com.example.codelesson.util.UserViewModel

@Composable
fun Security(innerPadding: PaddingValues, viewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {

        mainLetters("Seguridad")

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {


            securityTextFields("Contrasenia actual")

            securityTextFields("Contrasenia nueva")

            securityTextFields("Confirmar contrasenia nueva")
        }


        Row (
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){
            profilBbutton("ACTUALIZAR") {

            }

        }
    }
}

@Preview
@Composable
fun SecurityPreview() {
    Security(PaddingValues(0.dp), UserViewModel())
}


@Composable
fun securityTextFields(label : String, ) {
    Spacer(modifier = androidx.compose.ui.Modifier.height(25.dp))

        var password by rememberSaveable { mutableStateOf("") }
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