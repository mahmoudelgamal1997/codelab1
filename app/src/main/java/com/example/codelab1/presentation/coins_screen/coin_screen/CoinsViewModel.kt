package com.example.codelab1.presentation.coins_screen.coin_screen

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelab1.Resource
import com.example.codelab1.data.models.Coin
import com.example.codelab1.data.models.CoinsDto
import com.example.codelab1.domain.usecases.GetCoinsUseCase
import com.example.codelab1.presentation.coins_screen.state.CoinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor (val useCase: GetCoinsUseCase ) :ViewModel() {

    init {
        getCoin("")
    }
val _coin =  MutableStateFlow<CoinState> (CoinState())
   val coins :StateFlow<CoinState> = _coin
    fun getCoin(coin:String){
        viewModelScope.launch {
        useCase(coin).collect{
            when(it){
                is Resource.Loaded->_coin.value= CoinState(isLoading = false,coins=it.data!!.coins)

                is Resource.Loading -> _coin.value = CoinState(isLoading = true)

                is Resource.Error -> _coin.value = CoinState(isError = it.message.toString())

                is Resource.Empty -> _coin.value = CoinState()
            }

        }
    }
    }
}