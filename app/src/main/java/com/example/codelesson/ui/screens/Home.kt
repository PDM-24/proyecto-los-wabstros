package com.example.codelesson.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.codelesson.data.getName
import com.example.codelesson.ui.components.homecomponents.ButtonNavigate
import com.example.codelesson.ui.components.navigation.Graph
import com.example.codelesson.ui.theme.CodeLessonTheme
import com.example.codelesson.util.HomeViewModel

@Composable
fun Home(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    val title by viewModel.titleTopBar.collectAsState()
    CodeLessonTheme {
       LazyColumn(
           modifier = Modifier
               .fillMaxSize()
               .padding(innerPadding)
               .padding(top = 24.dp, bottom = 4.dp),
           verticalArrangement = Arrangement.spacedBy(24.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           items(getName()) {
               ButtonNavigate(
                   modifier = Modifier
                       .width(300.dp)
                       .height(65.dp),
                   it.name,
               ) {
                   viewModel.setTitle(it.name)
                   navController.navigate("${Graph.QUIZ.graph}/${it.topic}")
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
        HomeViewModel()
    )
}