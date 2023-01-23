package com.example.codelab1.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelab1.presentation.auth.CustomTextField
import com.example.codelab1.presentation.component.CustomButton

@Composable
fun RegisterScreen(
    navigateToLogin:()->Unit,
    navigateToCoinsScreen:()->Unit
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")  }
    var isError by remember { mutableStateOf(false) }
    var isPasswordVisible by remember {mutableStateOf(false)}
  Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
          .fillMaxSize()
          .background(Color.Black)) {
      Spacer(modifier = Modifier.height(15.dp))
      Icon(imageVector = Icons.Default.ArrowBack, tint = Color.White, contentDescription = "",
          modifier = Modifier
              .align(Alignment.Start)
              .clickable {
                  navigateToLogin()
              })
      Spacer(modifier = Modifier.height(30.dp))
      Text(text = "Create Account", color = Color.White, fontSize = 22.sp, modifier = Modifier
          .align(Alignment.Start)
          .padding(start = 8.dp))

      Text(text = "Register to get access to dashboard",color= Color.White , fontSize = 18.sp, modifier = Modifier
          .align(Alignment.Start)
          .padding(start = 8.dp))

      Spacer(modifier = Modifier.height(130.dp))

      CustomTextField(
          text =  email,
          placeholder = "Email",
          onValueChange = {email=it},
          errorMsg = "Email is required",
          isError = isError,
          isPasswordText = false,
          modifier = Modifier
      )

      Spacer(modifier = Modifier.height(20.dp))

      CustomTextField(
          text =  password,
          placeholder = "Password",
          onValueChange = { password = it },
          errorMsg = "Password is required",
          isError = isError,
          isPasswordText = false,
          modifier = Modifier,
          trailingIcon = {
              IconButton(onClick = { isPasswordVisible=!isPasswordVisible}) {
              Icon(imageVector =  if (isPasswordVisible) Icons.Default.Add else Icons.Default.Favorite, contentDescription = "")
          }
  }
      )
      Spacer(modifier = Modifier.height(40.dp))

      CustomButton(content = "Register", onclick = {
          isError = email.isEmpty() || password.isEmpty()
//          if (!isError) loginViewModel.login(email, password)
          navigateToCoinsScreen()
      })
  }

}