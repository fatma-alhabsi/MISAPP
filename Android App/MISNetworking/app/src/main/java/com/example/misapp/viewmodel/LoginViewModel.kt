package com.example.misapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.misapp.data.repository.AuthRepository

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        authRepository.loginUser(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onError = onError
        )
    }
}