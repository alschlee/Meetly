package com.example.aph4.feature_auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.aph4.R

class SignupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val btnSignup = view.findViewById<Button>(R.id.btn_signup)
        btnSignup.setOnClickListener {
            // 회원가입 성공했다치고 로그인 화면으로 이동
            (activity as? AuthActivity)?.navigateToLogin()
        }

        // 로그인 화면 이동 버튼
        val btnLogin = view.findViewById<View>(R.id.tv_login_link)
        btnLogin?.setOnClickListener {
            (activity as? AuthActivity)?.navigateToLogin()
        }

        return view
    }
}
