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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misapp.data.model.ServiceRequest

@Composable
fun ServiceRequestClientDetailsScreen(
    request: ServiceRequest,
    onBack: () -> Unit
) {

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

        // --------------------------------
        // HEADER
        // --------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }

            Text(
                text = "Request Details",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    top = 12.dp
                )
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // --------------------------------
        // REQUEST INFORMATION
        // --------------------------------

        Card(
            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surface
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = request.serviceCategory,

                    style =
                        MaterialTheme.typography.headlineSmall,

                    color =
                        MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                // Request ID

                DetailItem(
                    label = "Request ID",
                    value = request.requestId
                )


                // Priority

                DetailItem(
                    label = "Priority",
                    value = request.priority
                )


                // Status

                DetailItem(
                    label = "Status",
                    value = request.status
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                // Description

                Text(
                    text = "Description",

                    style =
                        MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = request.description,

                    style =
                        MaterialTheme.typography.bodyLarge
                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                // --------------------------------
                // TECHNICIAN
                // --------------------------------

                Text(
                    text = "Assigned Technician",

                    style =
                        MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text =
                        if (
                            request.assignedTechnicianName
                                .isBlank()
                        ) {

                            "Not assigned yet"

                        } else {

                            request.assignedTechnicianName
                        },

                    style =
                        MaterialTheme.typography.bodyLarge
                )


                Spacer(
                    modifier = Modifier.height(16.dp)
                )


                // --------------------------------
                // DEADLINE
                // --------------------------------

                Text(
                    text = "Deadline",

                    style =
                        MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text =
                        if (
                            request.deadline.isBlank()
                        ) {

                            "Not assigned"

                        } else {

                            request.deadline
                        },

                    style =
                        MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Composable
private fun DetailItem(
    label: String,
    value: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 14.dp
            )
    ) {

        Text(
            text = label,

            style =
                MaterialTheme.typography.labelMedium,

            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(3.dp)
        )

        Text(
            text = value,

            style =
                MaterialTheme.typography.bodyLarge
        )
    }
}