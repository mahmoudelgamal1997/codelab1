package com.example.codelab1.data.models

data class Client(
    val action: Any,
    val avg_rates: Any,
    val city: Any,
    val city_id: Any,
    val created_at: String,
    val email: String,
    val fb_token: String,
    val gender: String,
    val governorate: Any,
    val governorate_id: Any,
    val id: Int,
    val id_back: Any,
    val id_front: Any,
    val image: String,
    val image_path: String,
    val is_verified: String,
    val latitude: Any,
    val longitude: Any,
    val name: String,
    val phone: String,
    val social_id: Any,
    val tokens: List<Token>,
    val updated_at: String
)