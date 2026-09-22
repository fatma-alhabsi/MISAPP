package com.example.misapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misapp.data.model.ServiceRequest
import com.example.misapp.viewmodel.ServiceRequestViewModel

@Composable
fun MyServiceRequestsScreen(
    clientId: String,
    onBack: () -> Unit,
    onRequestClick: (ServiceRequest) -> Unit,
    viewModel: ServiceRequestViewModel = viewModel()
) {

    var requests by remember {
        mutableStateOf<List<ServiceRequest>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    fun loadRequests() {

        isLoading = true
        errorMessage = null

        viewModel.getClientRequests(

            clientId = clientId,

            onSuccess = { result ->

                requests = result.sortedByDescending {
                    it.createdAt
                }

                isLoading = false
            },

            onError = { error ->

                errorMessage = error

                isLoading = false
            }
        )
    }

    LaunchedEffect(clientId) {
        loadRequests()
    }

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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
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
                    text = "My Service Requests",
                    style =
                        MaterialTheme.typography.headlineSmall
                )
            }

            IconButton(
                onClick = {
                    loadRequests()
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh requests"
                )
            }
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // ========================================
        // CONTENT
        // ========================================

        when {

            // --------------------------------
            // LOADING
            // --------------------------------

            isLoading -> {

                Text(
                    text = "Loading your requests...",
                    style =
                        MaterialTheme.typography.bodyLarge
                )
            }


            // --------------------------------
            // ERROR
            // --------------------------------

            errorMessage != null -> {

                Text(
                    text =
                        errorMessage
                            ?: "Unable to load requests",

                    color =
                        MaterialTheme.colorScheme.error,

                    style =
                        MaterialTheme.typography.bodyLarge
                )
            }


            // --------------------------------
            // EMPTY
            // --------------------------------

            requests.isEmpty() -> {

                Column(
                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Spacer(
                        modifier =
                            Modifier.height(40.dp)
                    )

                    Text(
                        text =
                            "No service requests yet.",

                        style =
                            MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.height(8.dp)
                    )

                    Text(
                        text =
                            "Your submitted IT requests will appear here.",

                        style =
                            MaterialTheme.typography.bodyMedium
                    )
                }
            }


            // --------------------------------
            // REQUEST LIST
            // --------------------------------

            else -> {

                LazyColumn(

                    modifier =
                        Modifier.fillMaxSize(),

                    contentPadding =
                        PaddingValues(
                            bottom = 20.dp
                        ),

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    items(requests) { request ->

                        ServiceRequestCard(

                            request = request,

                            onClick = {

                                onRequestClick(
                                    request
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}


// ========================================
// SERVICE REQUEST CARD
// ========================================

@Composable
private fun ServiceRequestCard(
    request: ServiceRequest,
    onClick: () -> Unit
) {

    Card(

        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },

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
            // SERVICE CATEGORY
            // ========================================

            Text(
                text =
                    request.serviceCategory,

                style =
                    MaterialTheme.typography.titleLarge,

                color =
                    MaterialTheme.colorScheme.primary
            )


            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )


            // ========================================
            // DESCRIPTION
            // ========================================

            Text(
                text =
                    request.description,

                style =
                    MaterialTheme.typography.bodyMedium
            )


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            // ========================================
            // PRIORITY + STATUS
            // ========================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Priority",

                        style =
                            MaterialTheme.typography.labelMedium,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text =
                            request.priority,

                        style =
                            MaterialTheme.typography.bodyMedium
                    )
                }


                Column(
                    horizontalAlignment =
                        Alignment.End
                ) {

                    Text(
                        text = "Status",

                        style =
                            MaterialTheme.typography.labelMedium,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text =
                            request.status,

                        style =
                            MaterialTheme.typography.bodyMedium,

                        color =
                            MaterialTheme.colorScheme.primary
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )


            // ========================================
            // ASSIGNED TECHNICIAN
            // ========================================

            Text(
                text =
                    "Assigned Technician",

                style =
                    MaterialTheme.typography.labelMedium,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
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


            // ========================================
            // DEADLINE
            // ========================================

            if (
                request.deadline.isNotBlank()
            ) {

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(
                    text = "Deadline",

                    style =
                        MaterialTheme.typography.labelMedium,

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text =
                        request.deadline,

                    style =
                        MaterialTheme.typography.bodyMedium
                )
            }


            // ========================================
            // WORK NOTES
            // ========================================

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(
                text = "Work Notes",

                style =
                    MaterialTheme.typography.labelMedium,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    if (request.workNotes.isBlank()) {

                        "No work notes added yet"

                    } else {

                        request.workNotes
                    },

                style =
                    MaterialTheme.typography.bodyMedium
            )


            // ========================================
            // RESOLUTION
            // ========================================

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(
                text = "Resolution",

                style =
                    MaterialTheme.typography.labelMedium,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    if (request.resolution.isBlank()) {

                        "No resolution added yet"

                    } else {

                        request.resolution
                    },

                style =
                    MaterialTheme.typography.bodyMedium
            )


            // ========================================
            // OPEN DETAILS
            // ========================================

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Text(
                text =
                    "Tap to view full details",

                style =
                    MaterialTheme.typography.labelMedium,

                color =
                    MaterialTheme.colorScheme.primary
            )
        }
    }
}