package com.example.misapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SystemSettingsScreen(
    onBack: () -> Unit
) {

    val firestore = FirebaseFirestore.getInstance()

    var appName by remember {
        mutableStateOf("MISAPP")
    }

    var defaultSlaHours by remember {
        mutableStateOf("24")
    }

    var maintenanceMode by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var message by remember {
        mutableStateOf("")
    }

    // --------------------------------------
    // LOAD SETTINGS
    // --------------------------------------

    LaunchedEffect(Unit) {

        firestore
            .collection("systemSettings")
            .document("main")
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    appName =
                        document.getString("appName")
                            ?: "MISAPP"

                    defaultSlaHours =
                        document.getLong("defaultSlaHours")
                            ?.toString()
                            ?: "24"

                    maintenanceMode =
                        document.getBoolean("maintenanceMode")
                            ?: false
                }

                isLoading = false
            }
            .addOnFailureListener {

                isLoading = false

                message =
                    "Unable to load settings."
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) {

        // --------------------------------------
        // HEADER
        // --------------------------------------

        Row(
            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector =
                        Icons.Default.ArrowBack,

                    contentDescription =
                        "Back"
                )
            }

            Text(
                text =
                    "System Settings",

                style =
                    MaterialTheme
                        .typography
                        .headlineMedium,

                color =
                    MaterialTheme
                        .colorScheme
                        .primary
            )
        }

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        if (isLoading) {

            Text(
                text =
                    "Loading settings..."
            )

        } else {

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .surface
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(18.dp)
                ) {

                    Text(
                        text =
                            "Application Settings",

                        style =
                            MaterialTheme
                                .typography
                                .titleLarge
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // App Name

                    OutlinedTextField(
                        value =
                            appName,

                        onValueChange = {

                            appName =
                                it
                        },

                        label = {

                            Text(
                                "Application Name"
                            )
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        singleLine =
                            true
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // Default SLA

                    OutlinedTextField(
                        value =
                            defaultSlaHours,

                        onValueChange = {

                            defaultSlaHours =
                                it
                        },

                        label = {

                            Text(
                                "Default SLA (Hours)"
                            )
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        singleLine =
                            true
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    // Maintenance Mode

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Column(
                            modifier =
                                Modifier.weight(1f)
                        ) {

                            Text(
                                text =
                                    "Maintenance Mode",

                                style =
                                    MaterialTheme
                                        .typography
                                        .titleMedium
                            )

                            Text(
                                text =
                                    "Temporarily disable normal system access.",

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodySmall
                            )
                        }

                        Switch(
                            checked =
                                maintenanceMode,

                            onCheckedChange = {

                                maintenanceMode =
                                    it
                            }
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    Button(
                        onClick = {

                            val settings: Map<String, Any> =
                                mapOf(

                                    "appName" to appName,

                                    "defaultSlaHours" to
                                            (defaultSlaHours.toLongOrNull()
                                                ?: 24L),

                                    "maintenanceMode" to
                                            maintenanceMode
                                )

                            firestore
                                .collection(
                                    "systemSettings"
                                )
                                .document(
                                    "main"
                                )
                                .set(
                                    settings
                                )
                                .addOnSuccessListener {

                                    message =
                                        "Settings saved successfully!"
                                }
                                .addOnFailureListener {

                                    message =
                                        "Failed to save settings."
                                }
                        },

                        modifier =
                            Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text =
                                "SAVE SETTINGS"
                        )
                    }

                    if (
                        message.isNotEmpty()
                    ) {

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Text(
                            text =
                                message,

                            color =
                                MaterialTheme
                                    .colorScheme
                                    .primary
                        )
                    }
                }
            }
        }
    }
}