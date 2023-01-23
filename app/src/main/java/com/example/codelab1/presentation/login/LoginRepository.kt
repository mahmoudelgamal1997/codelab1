package com.example.codelab1.presentation.login

import com.example.codelab1.data.ApiInterface
import javax.inject.Inject


class LoginRepository @Inject constructor(val api:ApiInterface){

  suspend fun login(username:String,password:String) = api.login(username,password,"aaa")
}