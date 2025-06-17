package com.example.aph4.feature_auth.model

data class AuthResult(
    val success: Boolean,
    val message: String,
    val userId: String? = null
)
