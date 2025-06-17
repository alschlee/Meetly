package com.example.aph4.feature_auth.data

import com.example.aph4.feature_auth.model.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signup(email: String, password: String, nickname: String): AuthResult {
        return withContext(Dispatchers.IO) {
            // TODO: 실제로는 네트워크 요청
            remoteDataSource.signup(email, password, nickname)
        }
    }

    override suspend fun login(email: String, password: String): AuthResult {
        return withContext(Dispatchers.IO) {
            remoteDataSource.login(email, password)
        }
    }
}
