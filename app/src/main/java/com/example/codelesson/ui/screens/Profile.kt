package com.example.codelesson.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import com.example.codelesson.util.UserViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelesson.R
import com.example.codelesson.ui.components.homecomponents.ButtonNavigate
import com.example.codelesson.ui.components.profileComponents.mainLetters
import com.example.codelesson.ui.theme.DarkGrey
import com.example.codelesson.ui.theme.FocusedFormWhite
import com.example.codelesson.ui.theme.FormWhite
import com.example.codelesson.ui.theme.LetterTransparentBlack
import com.example.codelesson.ui.theme.MoreTransparentWhite
import com.example.codelesson.ui.theme.TopBarGrey

//val imagen: bitmap =
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile (innerPadding: PaddingValues, viewModel: UserViewModel){
    Scaffold (
        topBar = {
            TopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LetterTransparentBlack)
                    .padding(24.dp)
            )
        }

    ){
        innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxSize()
                .background(color = TopBarGrey),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            //Image(bitmap = , contentDescription = )
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                onClick = { }
            ) {

            }
            
            mainLetters(name = "Mi progreso")

        }
    }
}

@Preview
@Composable
fun ProfilePreview(){
    Profile(innerPadding = PaddingValues(0.dp))
}