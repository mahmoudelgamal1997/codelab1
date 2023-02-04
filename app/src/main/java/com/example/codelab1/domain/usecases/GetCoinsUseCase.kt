package com.example.codelab1.domain.usecases

import com.example.codelab1.Resource
import com.example.codelab1.data.models.CoinsDto
import com.example.codelab1.domain.repositories.RepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    val repo: RepositoryInterface) {


    operator fun invoke(coin: String) = flow<Resource<CoinsDto>> {

        try {
            val response = repo.getCoins(coin)
            if (response.isSuccessful) {
                emit(Resource.Loaded(response.body()!!))
            }
        } catch (ex: HttpException) {
            emit(Resource.Error(message = "un known error"))
        } catch (ex: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }
}