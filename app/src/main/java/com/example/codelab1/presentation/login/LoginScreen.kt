package com.example.codelab1.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codelab1.Resource
import com.example.codelab1.presentation.auth.CustomTextField
import com.example.codelab1.presentation.component.CustomButton
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun LoginScreen(
    navigateToCoinsScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    popUpStack: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val loginState = loginViewModel.loginStateFlow.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(modifier = Modifier.align(Alignment.Start).padding(start = 12.dp), text = "Welcome to Our app", color = Color.White, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Login to track your crypto assets",
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(100.dp))

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = email,
            onValueChange = { email = it },
            placeholder = "Email",
            isPasswordText = false,
            errorMsg = "Enter valid Email adress",
            isError = isError,
            trailingIcon = {
                if (email.isNotBlank()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
                    }
                }
            }

        )
        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = password,
            onValueChange = { password = it },
            placeholder = "Password",
            isPasswordText = true,
            errorMsg = "Wrong Password",
            isError = isError,
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Clear else Icons.Default.Favorite,
                        tint=Color.White,
                        contentDescription = ""
                    )

                }

            }
        )

        Spacer(modifier = Modifier.height(50.dp))
        CustomButton(content = "Login", onclick = {
            isError = email.isEmpty() || password.isEmpty()
            if (!isError) loginViewModel.login(email, password)

        })
        when (loginState.value) {
            is Resource.Error -> {
                Text(text = "email or password is wrong", color = Color.Red)
            }
            is Resource.Loaded -> {
                LaunchedEffect(Unit) {
                    navigateToCoinsScreen()
                }
            }
            is Resource.Loading -> {
                Box(modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                ) {
                    CircularProgressIndicator()
                }
            }

        }

        Row(verticalAlignment = Alignment.Bottom) {

            Text(text = "New User?",color = Color.White)
            Text(text = "Register", color = Color.Yellow, modifier = Modifier.clickable {
                navigateToSignUpScreen()
            })

        }
    }

}

@Preview
@Composable
fun preview() {
    LoginScreen(
        navigateToCoinsScreen = { /*TODO*/ },
        navigateToSignUpScreen = { /*TODO*/ },
        popUpStack = { /*TODO*/ })
}
