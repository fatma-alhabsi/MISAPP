package com.example.misapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.misapp.data.model.UserProfile
import com.example.misapp.data.repository.AuthRepository
import com.example.misapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository()
    private val userRepository = UserRepository()

    fun register(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        authRepository.registerUser(
            email = email,
            password = password,

            onSuccess = {

                val currentUser = FirebaseAuth
                    .getInstance()
                    .currentUser

                if (currentUser == null) {

                    onError(
                        "Account created, but user information could not be found."
                    )

                    return@registerUser
                }

                val userProfile = UserProfile(

                    uid = currentUser.uid,

                    fullName = fullName,

                    email = email,

                    phone = phone,

                    role = "client"
                )

                userRepository.createUserProfile(
                    userProfile = userProfile,

                    onSuccess = {

                        onSuccess()
                    },

                    onError = { errorMessage ->

                        onError(errorMessage)
                    }
                )
            },

            onError = { errorMessage ->

                onError(errorMessage)
            }
        )
    }
}