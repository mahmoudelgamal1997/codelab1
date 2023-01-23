package com.example.codelab1.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codelab1.presentation.login.LoginRepository
import com.example.codelab1.Resource
import com.example.codelab1.data.models.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var repository: LoginRepository):ViewModel(){

    val loginStateFlow= MutableStateFlow<Resource<LoginResponse?>>(Resource.Empty())

    fun login(username:String,password:String){

        viewModelScope.launch {
            loginStateFlow.value = Resource.Loading()
            var response = repository.login(username,password)
            if (response.isSuccessful){
                loginStateFlow.value = Resource.Loaded(data = response.body())
            }else {
                var error = response.errorBody()?.string()
                var errorMsg= JSONObject(error)
                Log.e("ggggg",errorMsg.optString("error"))


                loginStateFlow.value = Resource.Error(message = errorMsg.optString("error"))
            }

        }
    }
}