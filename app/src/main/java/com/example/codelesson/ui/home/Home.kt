package com.example.codelesson.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.homecomponents.ButtonNavigate
import com.example.codelesson.ui.components.navigation.QuizGraph
import com.example.codelesson.ui.theme.CodeLessonTheme
import com.example.codelesson.util.PracticeViewModel
import com.example.codelesson.util.UserViewModel

@Composable
fun Home(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: PracticeViewModel,
    userViewModel: UserViewModel,
) {
    val titleList by viewModel.titleList.collectAsState()
    val status by viewModel.completed.collectAsState()
    LaunchedEffect(Unit) {
        userViewModel.getUser()
    }

    CodeLessonTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 24.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(titleList) {
                ButtonNavigate(
                    modifier = Modifier
                        .width(300.dp)
                        .height(65.dp),
                    it.title,
                    viewModel
                ) {
                    viewModel.setId(it.id)
                    viewModel.setTitle(it.title.uppercase())
                    viewModel.getLesson()
                }
            }
        }
        if (status) {
            navController.navigate(QuizGraph.Theory.route)
            viewModel.setCompleted(false)
        }
    }
}