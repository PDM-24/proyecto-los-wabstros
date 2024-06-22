package com.example.codelesson.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.codelesson.data.ResumenList
import com.example.codelesson.data.DataResumen
import com.example.codelesson.ui.components.navigation.HomeGraph
import com.example.codelesson.ui.theme.audioWide
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.PracticeViewModel

@Composable
fun LessonRecap(innerPadding: PaddingValues, navController: NavHostController, practiceViewModel: PracticeViewModel) {

    Surface(
        color = Color(0xFF000000),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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

            //Recap
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                items(ResumenList()) { resumen ->
                    ResumenItem(resumen)
                }

            }

            //Buton de terminar
            Button(
                onClick = { navController.navigate(HomeGraph.Home.route) },
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
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


@Composable
fun ResumenItem(dataResumen: DataResumen) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)

    ) {
        Text(
            text = dataResumen.resumen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppins,
            color = Color(0xFFFFFFFF)
        )
    }
}
