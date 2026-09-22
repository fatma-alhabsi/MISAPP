package com.example.misapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.misapp.navigation.AppNavigation
import com.example.misapp.ui.theme.MISAPPTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        FirebaseApp.initializeApp(this)

        Log.d("MISAPP_FIREBASE", "Firebase initialized successfully")

        setContent {
            MISAPPTheme {
                AppNavigation()
            }
        }
    }
}