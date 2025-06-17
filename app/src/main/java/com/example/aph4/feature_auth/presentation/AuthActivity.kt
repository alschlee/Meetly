package com.example.aph4.feature_auth.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // 최초 진입 시 로그인 화면 표시
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.auth_fragment_container, LoginFragment())
                .commit()
        }
    }

    // 회원가입 화면으로 이동
    fun navigateToSignup() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_fragment_container, SignupFragment())
            .addToBackStack(null)
            .commit()
    }

    // 로그인 화면으로 이동
    fun navigateToLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_fragment_container, LoginFragment())
            .addToBackStack(null)
            .commit()
    }
}