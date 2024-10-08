package com.example.codelesson.ui.theory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.practicecomponents.BlackBoxText
import com.example.codelesson.ui.components.practicecomponents.CodeBlock
import com.example.codelesson.ui.profile.profilBbutton
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.util.PracticeViewModel

@Composable
fun Theory(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: PracticeViewModel
) {
    val lesson by viewModel.getLesson.collectAsState()
    val theory = remember {
        mutableStateOf(lesson.lesson)
    }
    val nextRoute by viewModel.nextNavigationRoute.collectAsState()

    if(nextRoute == "")
        viewModel.verifyTypeOfQuestion(0)

    LaunchedEffect(true) {
        viewModel.resetNavRoute()
        viewModel.resetIndex()
    }

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
                text = theory.value.uppercase()

            )
        }


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.Center

        ){
            profilBbutton(name = "SEGUIR") {
                if(nextRoute != ""){
                    navController.navigate(nextRoute)

                    viewModel.resetNavRoute()

                    viewModel.addIndex()
                }
            }

        }
    }
}

@Preview
@Composable
private fun Preview() {
    Theory(innerPadding = PaddingValues(), navController = NavHostController(LocalContext.current), viewModel =PracticeViewModel() )
}