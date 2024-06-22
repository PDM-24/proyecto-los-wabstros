package com.example.codelesson.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.practicecomponents.BlackBoxText
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.poppins
import com.example.codelesson.util.PracticeViewModel

@Composable
fun Theory(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: PracticeViewModel
) {
    val titleTheory by viewModel.titleTopBar.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .fillMaxWidth()
            .background(DarkGrey)
            .padding(top = 100.dp),
    ) {
        CodeBlock {
            BlackBoxText(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.".uppercase()

            )
        }


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center

        ){
            profilBbutton(name = "SEGUIR") {

            }


        }
    }
}

@Preview
@Composable
private fun Preview() {
    Theory(innerPadding = PaddingValues(), navController = NavHostController(LocalContext.current), viewModel =PracticeViewModel() )
}