package com.example.codelesson.ui.screens

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.codelesson.ui.components.homecomponents.ButtonNavigate
import com.example.codelesson.ui.components.navigation.QuizGraph
import com.example.codelesson.ui.theme.CodeLessonTheme
import com.example.codelesson.util.PracticeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Home(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: PracticeViewModel,
) {
    val titleList by viewModel.titleList.collectAsState()

    CodeLessonTheme {
        val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope
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
                    lifeCycleScope.launch {
                        Log.d("Lesson", it.id)
                        viewModel.setId(it.id)
                        viewModel.setTitle(it.title.uppercase())
                        viewModel.getLesson()
                        delay(600)
                        navController.navigate(QuizGraph.Theory.route)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home(
        PaddingValues(),
        NavHostController(LocalContext.current),
        PracticeViewModel()
    )
}