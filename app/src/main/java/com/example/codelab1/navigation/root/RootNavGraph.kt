package com.example.codelab1.navigation.root

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codelab1.navigation.auth.authNavigation
import com.example.codelab1.navigation.main.MainScreen

@ExperimentalMaterialApi
@Composable
fun RootNavGraph(navHostControler:NavHostController) {

    NavHost(
        navController = navHostControler,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavigation(navHostController = navHostControler)
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}
object Graph{
    const val ROOT="root_root"
    const val AUTH= "auth_root"
    const val MAIN = "main_root"
}