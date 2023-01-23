package com.example.codelab1.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CoinScreen(navHostController: NavHostController){

    Text(text = "Coin Screen")
}

@Composable
fun CoinsWatchList(navHostController: NavHostController){

    Text(text = "CoinsWatchList")
}
@Composable
fun CoinsNews(navHostController: NavHostController){

    Text("CoinsNews")
}