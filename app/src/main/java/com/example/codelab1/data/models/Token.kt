package com.example.codelab1.data.models

data class Token(
    val client_id: Int,
    val created_at: String,
    val expires_at: String,
    val id: String,
    val name: String,
    val revoked: Boolean,
    val scopes: List<Any>,
    val updated_at: String,
    val user_id: Int
)