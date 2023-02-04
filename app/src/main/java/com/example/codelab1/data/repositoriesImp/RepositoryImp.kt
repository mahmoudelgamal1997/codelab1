package com.example.codelab1.data.repositoriesImp

import com.example.codelab1.data.ApiInterface
import com.example.codelab1.data.models.CoinsDto
import com.example.codelab1.domain.repositories.RepositoryInterface
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(val api:ApiInterface) : RepositoryInterface {
    override suspend fun getCoins(coins: String): Response<CoinsDto> {
        return api.getCoins(coins)
    }

}