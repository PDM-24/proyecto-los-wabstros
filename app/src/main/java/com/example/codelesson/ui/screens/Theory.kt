package com.example.codelesson.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.codelesson.data.theoryList
import com.example.codelesson.util.HomeViewModel

@Composable
fun Theory(
    innerPadding: PaddingValues,
    navController: NavHostController,
    viewModel: HomeViewModel,
    titleTheory: String?
) {
    val title by viewModel.titleTopBar.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("Titulo enviado", titleTheory.toString())
        var lesson: String = ""
        theoryList().forEach {
            Log.d("Titulo", it.title)
            if (it.title == titleTheory.toString()) {
                lesson = it.lesson
            }
        }

        Text(text = lesson)
    }
}