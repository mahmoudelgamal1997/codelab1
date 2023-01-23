package com.example.codelab1.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.codelab1.navigation.root.Graph
import com.example.codelab1.presentation.register.RegisterScreen
import com.example.codelab1.presentation.SplashScreen
import com.example.codelab1.presentation.login.LoginScreen

fun NavGraphBuilder.authNavigation(navHostController: NavHostController){

    navigation(route = Graph.AUTH, startDestination = AuthScreen.Splash.route){

        composable(route = AuthScreen.Splash.route){
            SplashScreen(goToLogin = {
                navHostController.popBackStack()
                navHostController.navigate(AuthScreen.SignIn.route)
            })
        }
        composable(
            route = AuthScreen.SignIn.route) {
            LoginScreen(navigateToCoinsScreen = {
                navHostController.popBackStack()
                navHostController.navigate(Graph.MAIN)
            },
            navigateToSignUpScreen = {
                navHostController.navigate(AuthScreen.SignUp.route)
            },
                popUpStack = {
                    navHostController.popBackStack(AuthScreen.SignUp.route,false)
                }
                )
        }
        composable(route=AuthScreen.SignUp.route){
            RegisterScreen(navigateToLogin = {
                navHostController.navigate(AuthScreen.SignIn.route)
            },
            navigateToCoinsScreen = {
                navHostController.navigate(Graph.MAIN)
            }
                )
        }
    }
}
sealed class AuthScreen(val route: String) {
    object SignUp: AuthScreen(route = "SIGN_UP")
    object SignIn: AuthScreen(route = "SIGN_IN")
    object Splash: AuthScreen(route = "SPLASH")

}