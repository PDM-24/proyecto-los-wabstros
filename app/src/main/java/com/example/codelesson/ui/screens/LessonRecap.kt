package com.example.codelesson.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.navigation.Graph
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.components.practicecomponents.BlackBoxText
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.util.PracticeViewModel

@Composable
fun LessonRecap (
    innerPadding: PaddingValues,
    practiceViewModel: PracticeViewModel,
    navController: NavHostController
){
    val recap = remember {
        mutableStateOf(practiceViewModel.practiceList.value.recap)
    }

    val backHandlerActive = remember {
        mutableStateOf(true)
    }

    val context = LocalContext.current

    BackHandler{
        if(backHandlerActive.value){
            backHandlerActive.value = false
            Toast.makeText(context, "Presiona de nuevo para regresar al menú principal", Toast.LENGTH_SHORT).show()
        }else{
            navController.navigate(HomeGraph.Home.route){
                popUpTo(Graph.HOME.graph)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .fillMaxWidth()
            .background(DarkGrey)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "FELICIDADES!!!",
                fontSize = 24.sp,
                fontWeight = FontWeight(500),
                fontFamily = audioWide,
                color = Color(0xFFAED32F)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, start = 26.dp, bottom = 10.dp),
        ) {
            Text(
                text = "En esta lección aprendiste:\n",
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                fontFamily = audioWide,
                color = Color(0xFFAED32F)
            )
        }
        CodeBlock {
            BlackBoxText(
                text = recap.value.uppercase()

            )
        }

        //Buton de terminar
        Button(
            onClick = { navController.navigate(Graph.HOME.graph) },
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFFFFFFFF),
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E1E1E)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "TERMINAR",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = audioWide,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF)
                )
            )
        }
    }
}
