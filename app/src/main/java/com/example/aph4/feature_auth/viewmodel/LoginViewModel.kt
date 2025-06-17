package com.example.aph4.feature_auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aph4.feature_auth.data.AuthRepository
import com.example.aph4.feature_auth.model.AuthResult
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginResult = MutableLiveData<AuthResult>()
    val loginResult: LiveData<AuthResult> get() = _loginResult

    fun login() {
        val emailValue = email.value.orEmpty()
        val passwordValue = password.value.orEmpty()

        if (emailValue.isBlank() || passwordValue.isBlank()) {
            _loginResult.value = AuthResult(false, "이메일과 비밀번호를 모두 입력해주세요.")
            return
        }

        viewModelScope.launch {
            val result = authRepository.login(emailValue, passwordValue)
            _loginResult.value = result
        }
    }
}
