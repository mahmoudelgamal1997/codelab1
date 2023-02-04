package com.example.codelab1.domain.repositories

import com.example.codelab1.data.models.Coin
import com.example.codelab1.data.models.CoinsDto
import org.w3c.dom.Text
import retrofit2.Response

interface RepositoryInterface {
    suspend fun getCoins(coins:String=""):Response<CoinsDto>
}