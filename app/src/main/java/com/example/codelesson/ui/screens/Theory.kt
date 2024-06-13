package com.example.codelesson.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
            .padding(16.dp),
    ) {

    }
}

@Preview
@Composable
private fun Preview() {
    Theory(innerPadding = PaddingValues(), navController = NavHostController(LocalContext.current), viewModel =PracticeViewModel() )
}