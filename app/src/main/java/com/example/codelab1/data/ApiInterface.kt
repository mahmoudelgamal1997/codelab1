package com.example.codelab1.data

import com.example.codelab1.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {


    @FormUrlEncoded
    @POST("login")
   suspend fun login (

        @Field("phone") phone:String,
        @Field("password") password:String,
        @Field ("fb_token") fb_token:String?="aaa"
    ): Response<LoginResponse>
}