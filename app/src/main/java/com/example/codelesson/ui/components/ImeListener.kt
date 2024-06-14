package com.example.codelesson.ui.components

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
/* If the keyboard is visible returns true, if it's not it returns false */
fun rememberImeState(): State<Boolean>{
    //input method state
    val imeState = remember {
        mutableStateOf(false)
    }

    //set the actual view
    val view = LocalView.current

    //Code that will run until the view is disposed
    DisposableEffect(view) {
        //Check if the keyboard is visible or not
        /* Observes the layoiut and then it checks if the keyboard is visible or not,
        * if it's visible it returns true, if it's not visible it returns false
        * if the result is null it returns true */
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            imeState.value = isKeyboardOpen
        }

        //Executes the listener on the actual composable view
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            //Clears the listener
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return imeState
}