package com.example.codelesson.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codelesson.R
import com.example.codelesson.ui.components.navigation.ProfileGraph
import com.example.codelesson.ui.components.profileComponents.dataLetters
import com.example.codelesson.ui.components.profileComponents.mainLetters
import com.example.codelesson.ui.components.profileComponents.secondaryLetters
import com.example.codelesson.ui.theme.ButtonPurple1
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.DividerPurple
import com.example.codelesson.ui.theme.FocusedFormWhite
import com.example.codelesson.ui.theme.FormWhite
import com.example.codelesson.ui.theme.NeonGreen
import com.example.codelesson.ui.theme.NeonPurple
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.util.UserViewModel

var porcentaje: Int = 39
@Composable
fun Profile (innerPadding: PaddingValues, viewModel: UserViewModel, navController: NavHostController){
    Column (
        modifier = Modifier
            .padding(innerPadding)
            .padding(start = 10.dp)
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = DarkGrey), //TopBarGrey
        //horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Image(bitmap = , contentDescription = )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.default_image),
                contentDescription = "profile photo"
            )
        }


        mainLetters(name = "Mi progreso")
        
        Text(
            text = "$porcentaje%",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 25.dp),
            textAlign = TextAlign.End,
            color = FocusedFormWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            fontFamily = audioWide
        )
        BarraDePorcentaje(porcentaje = porcentaje.toFloat() / 100)

        mainLetters(name = "Informacion personal")

        Row (
            modifier = Modifier
                .padding(start = 40.dp, top = 40.dp)
                .fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column (
                modifier = Modifier
                    .weight(0.5f)
                    //.background(color = Color.White)
            ){
                dataLetters(name = "Christina")
                secondaryLetters(name = "Nombre")
            }
            Column (
                modifier = Modifier
                    .weight(0.5f)
                    //.background(color = Color.Blue)
            ){
                dataLetters(name = "Salazar")
                secondaryLetters(name = "Apellido")
            }
        }

        Column (
            modifier = Modifier
                .padding(start = 40.dp, top = 50.dp)
                .fillMaxWidth()
        ){
            dataLetters(name = "alesalazar@gmail.com")
            secondaryLetters(name = "Correo")
        }

        Row (
            modifier = Modifier
                .padding(start = 20.dp, top = 50.dp, end = 20.dp)
                .fillMaxWidth()
        ){
            Column (
                modifier = Modifier
                    .weight(0.5f)
                    .height(200.dp)
            ){
                profilBbutton(name = "SEGURIDAD") {
                    navController.navigate(ProfileGraph.Security.route)
                }
                profilBbutton(name = "CERRAR SESION") {

                }
            }
            Column (
                modifier = Modifier
                    .weight(0.5f)
                    .height(100.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ){
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp),

                    onClick = {
                        navController.navigate(ProfileGraph.EditProfile.route)
                    }
                ) {
                    Text(
                        fontFamily = audioWide,
                        text = "Editar",
                        color = FormWhite,
                        fontSize = 15.sp
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.edit_pen),
                        contentDescription = "edit_pen",
                        tint =  FormWhite
                    )

                }

            }
        }

    }
}

@Preview
@Composable
fun ProfilePreview(){
    Profile(innerPadding = PaddingValues(0.dp), viewModel = UserViewModel(), navController = NavHostController(LocalContext.current))
}

@Composable
fun BarraDePorcentaje(porcentaje: Float) {

    LinearProgressIndicator(
        progress = porcentaje,
        color = NeonGreen,
        trackColor = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
            .height(20.dp)
            .clip(RoundedCornerShape(100.dp))
            //.border(2.dp, Color.Black, RoundedCornerShape(100.dp))

    )
}

@Composable
fun profilBbutton(name : String, onClick: () -> Unit ) {
    Button(
        modifier = Modifier
            .width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonPurple1, contentColor = Color.White),
        onClick = { onClick() }
    ) {
        Text(
            text = name,
            fontFamily = audioWide,
            fontSize = 13.sp
        )

    }
}

fun editar(){

}
//====================================================poner navController en profileGrpah como parametro =============================