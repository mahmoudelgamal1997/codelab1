package com.example.codelab1.presentation.coins_screen.state

import com.example.codelab1.data.models.Coin

data class CoinState(
    var isLoading:Boolean = false,
    var coins:List<Coin> = arrayListOf(),
    var isError:String=""
)
