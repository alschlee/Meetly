package com.example.aph4.feature_auth.data

import com.example.aph4.feature_auth.model.AuthResult

interface AuthRepository {
    suspend fun signup(email: String, password: String, nickname: String): AuthResult
    suspend fun login(email: String, password: String): AuthResult
}
