package com.example.aph4.feature_auth.data

import com.example.aph4.feature_auth.model.AuthResult

class AuthRemoteDataSource {
    suspend fun signup(email: String, password: String, nickname: String): AuthResult {
        // TODO: Retrofit으로 서버와 통신
        return if (email.contains("@")) {
            AuthResult(true, "회원가입 성공", userId = "12345")
        } else {
            AuthResult(false, "유효하지 않은 이메일입니다.")
        }
    }

    suspend fun login(email: String, password: String): AuthResult {
        // TODO: 실제로는 서버 통신
        return if (email == "jingyeong@test.com" && password == "1234") {
            AuthResult(true, "로그인 성공", userId = "12345")
        } else {
            AuthResult(false, "이메일 또는 비밀번호가 올바르지 않습니다.")
        }
    }
}
