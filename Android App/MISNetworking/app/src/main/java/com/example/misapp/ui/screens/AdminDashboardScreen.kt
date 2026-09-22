package com.example.misapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box


data class AdminMenuItem(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun AdminDashboardScreen(
    fullName: String,
    onLogout: () -> Unit,
    onServiceRequests: () -> Unit,
    onTechnicians: () -> Unit,
    onClients: () -> Unit,
    onSystemSettings: () -> Unit
) {

    val menuItems = listOf(

        AdminMenuItem(
            "Service Requests",
            "View and manage client requests",
            Icons.Default.Assignment
        ),

        AdminMenuItem(
            "Technicians",
            "Manage technicians and assignments",
            Icons.Default.SupportAgent
        ),

        AdminMenuItem(
            "Clients",
            "View registered clients",
            Icons.Default.People
        ),

        AdminMenuItem(
            "System Settings",
            "Manage MISAPP settings",
            Icons.Default.Settings
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(20.dp)
    ) {


        // ========================================
        // TOP BAR
        // ========================================

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Welcome, ADMIN",

                    style =
                        MaterialTheme.typography.titleLarge,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )

                Spacer(
                    modifier =
                        Modifier.height(3.dp)
                )

                Text(
                    text = fullName,

                    style =
                        MaterialTheme.typography.bodyMedium,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            Button(
                onClick = onLogout,

                shape =
                    RoundedCornerShape(50.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            MaterialTheme.colorScheme.primary
                    )
            ) {

                Text(
                    text = "Logout"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(32.dp)
        )


        // ========================================
        // PAGE TITLE
        // ========================================

        Text(
            text = "Administration",

            style =
                MaterialTheme.typography.headlineSmall,

            color =
                MaterialTheme.colorScheme.onBackground
        )

        Spacer(
            modifier =
                Modifier.height(5.dp)
        )

        Text(
            text =
                "Manage your MISAPP system",

            style =
                MaterialTheme.typography.bodyMedium,

            color =
                MaterialTheme.colorScheme.onBackground
                    .copy(alpha = 0.65f)
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // ========================================
        // MENU
        // ========================================

        Column(
            modifier =
                Modifier.fillMaxWidth(),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            AdminMenuCard(
                item = menuItems[0],
                onClick = onServiceRequests
            )

            AdminMenuCard(
                item = menuItems[1],
                onClick = onTechnicians
            )

            AdminMenuCard(
                item = menuItems[2],
                onClick = onClients
            )

            AdminMenuCard(
                item = menuItems[3],
                onClick = onSystemSettings
            )
        }
    }


}

// ========================================
// ADMIN MENU CARD
// ========================================

@Composable
private fun AdminMenuCard(
    item: AdminMenuItem,
    onClick: () -> Unit
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surface
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 18.dp,
                        vertical = 18.dp
                    ),

            verticalAlignment =
                Alignment.CenterVertically
        ) {


            // ========================================
            // BLUE ACCENT
            // ========================================

            Box(
                modifier =
                    Modifier
                        .width(5.dp)
                        .height(54.dp)
                        .background(
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .primary,

                            shape =
                                RoundedCornerShape(20.dp)
                        )
            )


            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )


            // ========================================
            // ICON CONTAINER
            // ========================================

            Box(
                modifier =
                    Modifier
                        .size(50.dp)
                        .background(
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .primary
                                    .copy(alpha = 0.12f),

                            shape =
                                RoundedCornerShape(16.dp)
                        ),

                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector =
                        item.icon,

                    contentDescription =
                        item.title,

                    tint =
                        MaterialTheme
                            .colorScheme
                            .primary,

                    modifier =
                        Modifier.size(26.dp)
                )
            }


            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )


            // ========================================
            // TEXT
            // ========================================

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        item.title,

                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurface
                )

                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )

                Text(
                    text =
                        item.description,

                    style =
                        MaterialTheme
                            .typography
                            .bodySmall,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurface
                            .copy(alpha = 0.60f)
                )
            }


            // ========================================
            // OPEN BUTTON
            // ========================================

            Button(
                onClick = onClick,

                shape =
                    RoundedCornerShape(12.dp),

                contentPadding =
                    PaddingValues(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    )
            ) {

                Text(
                    text = "OPEN"
                )
            }
        }
    }

}

