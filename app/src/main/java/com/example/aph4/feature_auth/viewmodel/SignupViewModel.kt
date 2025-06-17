package com.example.aph4.feature_auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aph4.feature_auth.data.AuthRepository
import com.example.aph4.feature_auth.model.AuthResult
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    val email = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirm = MutableLiveData<String>()

    private val _signupResult = MutableLiveData<AuthResult>()
    val signupResult: LiveData<AuthResult> get() = _signupResult

    fun signup() {
        val emailValue = email.value.orEmpty()
        val nicknameValue = nickname.value.orEmpty()
        val passwordValue = password.value.orEmpty()
        val passwordConfirmValue = passwordConfirm.value.orEmpty()

        if (emailValue.isBlank() || nicknameValue.isBlank() || passwordValue.isBlank() || passwordConfirmValue.isBlank()) {
            _signupResult.value = AuthResult(false, "모든 값을 입력해주세요.")
            return
        }
        if (passwordValue != passwordConfirmValue) {
            _signupResult.value = AuthResult(false, "비밀번호가 일치하지 않습니다.")
            return
        }

        viewModelScope.launch {
            val result = authRepository.signup(emailValue, passwordValue, nicknameValue)
            _signupResult.value = result
        }
    }
}
