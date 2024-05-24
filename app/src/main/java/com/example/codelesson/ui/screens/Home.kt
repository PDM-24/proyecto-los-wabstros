package com.example.codelesson.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelesson.data.getName
import com.example.codelesson.ui.components.homecomponents.ButtonNavigate
import com.example.codelesson.ui.theme.BottomBarBlack
import com.example.codelesson.ui.theme.CodeLessonTheme
import com.example.codelesson.ui.theme.DividerPurple
import com.example.codelesson.ui.theme.NeonGreen
import com.example.codelesson.ui.theme.TopBarGrey
import com.example.codelesson.ui.theme.audioWide

@Composable
fun Home(innerPadding: PaddingValues) {
    Scaffold(topBar = {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(TopBarGrey)
                .padding(24.dp)
        )
    }, bottomBar = {
        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(BottomBarBlack)
                .padding(12.dp)
        )
    }) { InnerPadding ->
        CodeLessonTheme {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(InnerPadding)
                    .padding(top = 24.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(getName()) {
                    ButtonNavigate(
                        modifier = Modifier
                            .width(300.dp)
                            .height(65.dp),
                        it.name
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Text(text = "Desarrollador C++", fontSize = 24.sp, fontFamily = audioWide)
    }
}

@Composable
fun BottomBar(
    modifier: Modifier
) {
    Column() {
        Divider(color = DividerPurple, thickness = 3.dp)
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Home,
                tint = NeonGreen,
                contentDescription = "Home Icon",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { })

            Spacer(modifier = Modifier.padding(24.dp))

            Icon(imageVector = Icons.Outlined.Person,
                tint = NeonGreen,
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(32.dp)
                    .clickable { })
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home(innerPadding = PaddingValues())
}