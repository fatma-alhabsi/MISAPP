package com.example.misapp.data.repository

import com.example.misapp.data.model.UserProfile
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun createUserProfile(
        userProfile: UserProfile,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        firestore
            .collection("users")
            .document(userProfile.uid)
            .set(userProfile)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onError(
                    exception.message
                        ?: "Failed to save user profile"
                )
            }
    }

    fun getUserProfile(
        uid: String,
        onSuccess: (UserProfile) -> Unit,
        onError: (String) -> Unit
    ) {

        firestore
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    val userProfile =
                        document.toObject(UserProfile::class.java)

                    if (userProfile != null) {

                        onSuccess(userProfile)

                    } else {

                        onError(
                            "Unable to read user profile"
                        )
                    }

                } else {

                    onError(
                        "User profile not found"
                    )
                }
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to load user profile"
                )
            }
    }
}