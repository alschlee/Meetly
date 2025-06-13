package com.example.aph4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aph4.feature_home.presentation.HomeFragment
import androidx.databinding.DataBindingUtil
import com.example.aph4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}
