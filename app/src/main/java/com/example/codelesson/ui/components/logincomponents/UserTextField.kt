package com.example.codelesson.ui.components.logincomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.codelesson.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTextField(
    value: String,
    onChange: (String) -> Unit,
    label: String = stringResource(id = R.string.login_username),
    placeholder: String = stringResource(id = R.string.login_placeholder_user)
){
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onChange,

        placeholder = { Text(text = placeholder,
            color = Color(0xFFE2E2E2)) },

        label = { Text(label, color = Color(0xFFFFFDFD)) },

        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .clip(RoundedCornerShape(percent = 25)),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFF8E8E8E),
            cursorColor = Color(0xFFFFFDFD),
            focusedIndicatorColor = Color(0xFFFFFDFD),
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color(0xFFFFFDFD)
        )
    )
}