package com.example.codelab1.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codelab1.navigation.root.Graph
import com.example.codelab1.presentation.CoinScreen
import com.example.codelab1.presentation.CoinsNews
import com.example.codelab1.presentation.CoinsWatchList


@Composable
fun MainGraph(navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        route = Graph.MAIN,
        startDestination = Screens.CoinsScreen.route){

        composable(route = Screens.CoinsScreen.route){
            CoinScreen(navHostController=navHostController)
        }

        composable(route = Screens.CoinsNews.route){
           CoinsNews(navHostController=navHostController)
        }
        composable(route = Screens.CoinsWatchList.route){
            CoinsWatchList(navHostController=navHostController)
        }

    }
}