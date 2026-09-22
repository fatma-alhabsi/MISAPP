package com.example.misapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.HomeRepairService
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material3.Button
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

data class ServiceItem(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun ClientDashboardScreen(
    fullName: String,
    onLogout: () -> Unit,
    onRequestService: () -> Unit,
    onMyRequests: () -> Unit
) {


    val services = listOf(

        ServiceItem(
            title = "Computer Support",
            description = "Desktop, laptop and hardware support",
            icon = Icons.Default.Build
        ),

        ServiceItem(
            title = "Network Support",
            description = "Network and connectivity troubleshooting",
            icon = Icons.Default.NetworkCheck
        ),

        ServiceItem(
            title = "Printer Support",
            description = "Printer and scanner configuration",
            icon = Icons.Default.HomeRepairService
        ),

        ServiceItem(
            title = "Email Support",
            description = "Email setup and troubleshooting",
            icon = Icons.Default.Email
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
        // HEADER
        // ========================================

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Welcome, $fullName",

                style =
                    MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = onLogout
            ) {

                Text(
                    text = "Logout"
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(30.dp)
        )


        // ========================================
        // PAGE TITLE
        // ========================================

        Text(
            text = "How can we help you?",

            style =
                MaterialTheme.typography.headlineSmall,

            color =
                MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        Text(
            text =
                "Select a service or submit an IT support request.",

            style =
                MaterialTheme.typography.bodyMedium
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        // ========================================
        // SERVICE CARDS
        // ========================================

        LazyVerticalGrid(

            columns =
                GridCells.Fixed(2),

            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),

            contentPadding =
                PaddingValues(4.dp),

            horizontalArrangement =
                Arrangement.spacedBy(14.dp),

            verticalArrangement =
                Arrangement.spacedBy(14.dp)

        ) {

            items(services) { service ->

                ClientServiceCard(
                    service = service
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(14.dp)
        )


        // ========================================
        // MY SERVICE REQUESTS
        // ========================================

        Button(
            onClick = onMyRequests,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(50.dp),

            shape =
                RoundedCornerShape(14.dp)
        ) {

            Text(
                text =
                    "MY SERVICE REQUESTS"
            )
        }


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        // ========================================
        // REQUEST IT SERVICE
        // ========================================

        Button(
            onClick = onRequestService,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(54.dp),

            shape =
                RoundedCornerShape(14.dp)
        ) {

            Text(
                text =
                    "REQUEST IT SERVICE"
            )
        }
    }
}

// ========================================
// MODERN SERVICE CARD
// ========================================

@Composable
private fun ClientServiceCard(
    service: ServiceItem
) {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surface
            )
    ) {

        Column(

            modifier =
                Modifier.padding(16.dp)
        ) {


            // ========================================
            // ICON CONTAINER
            // ========================================

            Box(

                modifier =
                    Modifier
                        .size(48.dp)
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
                        service.icon,

                    contentDescription =
                        service.title,

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
                    Modifier.height(16.dp)
            )


            // ========================================
            // TITLE
            // ========================================

            Text(

                text =
                    service.title,

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
                    Modifier.height(6.dp)
            )


            // ========================================
            // DESCRIPTION
            // ========================================

            Text(

                text =
                    service.description,

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
    }

}
