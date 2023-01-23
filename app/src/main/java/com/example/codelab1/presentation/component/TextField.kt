package com.example.codelab1.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Shape


@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isPasswordText: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMsg: String,
    modifier: Modifier
) {

    CustomTextFieldWithError(
        value = text, onValueChange = onValueChange, isError = isError,
        label = { Text(text = placeholder, color = Color.White) },
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        visualTransformation = if (isPasswordText) PasswordVisualTransformation() else VisualTransformation.None,
        errorMsg = errorMsg,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )

}

@Composable
fun CustomTextFieldWithError(
    value: String,

    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMsg: String = "",
    placeholder: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,

    ) {
    Column() {

        OutlinedTextField(
            value = value, onValueChange = onValueChange,
            modifier = modifier,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            isError = isError,
            placeholder = placeholder,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            label = label,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Gray,
                unfocusedBorderColor = Gray,
                cursorColor = Color.White, textColor = Color.White),
        )

        if (isError) {
            Text(text = errorMsg, modifier = Modifier.padding(12.dp), color = Color.Red)
        }

    }
}