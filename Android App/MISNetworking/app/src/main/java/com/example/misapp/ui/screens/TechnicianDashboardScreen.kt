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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TechnicianDashboardScreen(
    fullName: String,
    onMyRequests: () -> Unit,
    onLogout: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(20.dp)
    ) {

        // --------------------------------
        // HEADER
        // --------------------------------

        Text(
            text = "Technician Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Welcome, $fullName",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )


        // --------------------------------
        // ASSIGNED REQUESTS
        // --------------------------------

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surface
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Assignment,

                        contentDescription =
                            "Assigned Requests",

                        tint =
                            MaterialTheme.colorScheme.primary
                    )

                    Spacer(
                        modifier =
                            Modifier.padding(
                                horizontal = 8.dp
                            )
                    )

                    Text(
                        text = "My Assigned Requests",

                        style =
                            MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(
                    text =
                        "View service requests assigned to you.",

                    style =
                        MaterialTheme.typography.bodyMedium,

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Button(
                    onClick = onMyRequests,

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "MY ASSIGNED REQUESTS"
                    )
                }
            }
        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        // --------------------------------
        // LOGOUT
        // --------------------------------

        Button(
            onClick = onLogout,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector =
                    Icons.Default.Logout,

                contentDescription =
                    "Logout"
            )

            Spacer(
                modifier =
                    Modifier.padding(
                        horizontal = 4.dp
                    )
            )

            Text(
                text = "LOGOUT"
            )
        }
    }
}