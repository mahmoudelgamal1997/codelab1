package com.example.codelab1.data

import com.example.codelab1.data.models.CoinsDto
import com.example.codelab1.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @FormUrlEncoded
    @POST("login")
   suspend fun login (

        @Field("phone") phone:String,
        @Field("password") password:String,
        @Field ("fb_token") fb_token:String?="aaa"
    ): Response<LoginResponse>

    @GET("v1/coins")
    suspend fun getCoins(
        @Query("currency") currency: String = "USD",
        @Query("skip") skip: Int = 0
    ):Response<CoinsDto>

}