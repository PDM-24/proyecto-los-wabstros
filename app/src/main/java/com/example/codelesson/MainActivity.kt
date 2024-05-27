package com.example.codelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.codelesson.ui.screens.Main
import com.example.codelesson.ui.theme.CodeLessonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeLessonTheme {
                {/* TO DO */}
                Main()
            }
        }
    }
}