package com.example.codelab1.data.models

data class LoginResponse(
    val code: Int,
    val `data`: Data,
    val error: Any
)