package com.example.misapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.misapp.data.model.UserProfile
import com.example.misapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class UserSessionViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val auth = FirebaseAuth.getInstance()

    var currentUserProfile: UserProfile? = null
        private set

    fun loadCurrentUser(
        onSuccess: (UserProfile) -> Unit,
        onError: (String) -> Unit
    ) {

        val currentUser = auth.currentUser

        if (currentUser == null) {

            onError("No user is currently logged in")

            return
        }

        userRepository.getUserProfile(
            uid = currentUser.uid,

            onSuccess = { profile ->

                currentUserProfile = profile

                onSuccess(profile)
            },

            onError = { errorMessage ->

                onError(errorMessage)
            }
        )
    }

    fun logout() {

        auth.signOut()

        currentUserProfile = null
    }
}