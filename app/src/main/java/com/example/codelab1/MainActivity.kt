package com.example.codelab1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.codelab1.navigation.root.RootNavGraph
import com.example.codelab1.presentation.login.LoginViewModel
import com.example.codelab1.ui.theme.Codelab1Theme
import dagger.hilt.android.AndroidEntryPoint
import android.window.SplashScreen;
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

@OptIn(ExperimentalMaterialApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Codelab1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    RootNavGraph(navHostControler = rememberNavController())
                }
            }
        }
    }
}


@Composable
fun Login(viewModel: LoginViewModel, errorMsg: String? = null) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(value = username, onValueChange = {
            username = it
        })
        Spacer(Modifier.height(20.dp))

        TextField(
            value = password, onValueChange = {
                password = it
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )


        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            viewModel.login(username, password)
            Log.e("ddddddd username: ", username)
            Log.e("ddddddd password: ", password)
        }) {
            Text(text = "Login")
        }

        if (!errorMsg.isNullOrEmpty())  Text(errorMsg)

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Codelab1Theme {
    }
}