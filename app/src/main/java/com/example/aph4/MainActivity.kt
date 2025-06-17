package com.example.aph4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aph4.databinding.ActivityMainBinding
import com.example.aph4.feature_home.presentation.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // SharedPreferences에서 로그인 여부 확인
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
        // prefs.edit().clear().apply()
        val isLoggedIn = prefs.getBoolean("is_logged_in", false)

        if (!isLoggedIn) {
            // 로그인 안 됨 → AuthActivity로 이동
            val intent = Intent(this, com.example.aph4.feature_auth.presentation.AuthActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // 로그인 된 경우만 홈 화면 띄움
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}

