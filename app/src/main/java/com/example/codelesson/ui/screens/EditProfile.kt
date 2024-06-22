package com.example.codelesson.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.R
import com.example.codelesson.ui.components.profileComponents.mainLetters
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.util.UserViewModel

@Composable
fun EditProfile(innerPadding: PaddingValues, viewModel: UserViewModel) {
    Column (
        modifier = Modifier
            .padding(innerPadding)
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
            .fillMaxSize()
            .background(DarkGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            Box (
                modifier = Modifier
                    .padding(8.dp)
            ){
                Image(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.default_image), contentDescription = "profile photo"
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

        profileTexField("Chistina", "Nombre")

        profileTexField("Salazar", "Apellido" )

        profileTexField("alesalazar@gmail.com", "Correo electronico" )

        Spacer(modifier = Modifier.height(75.dp))

        profilBbutton("Actualizar") {
            
        }
    }

}

@Preview
@Composable
fun EditProfilePreview() {
    EditProfile(innerPadding = PaddingValues(0.dp), viewModel = UserViewModel())
}

@Composable
fun profileTexField(name : String, label : String){
    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        value = name,
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
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
        onValueChange = {}
    )
}
