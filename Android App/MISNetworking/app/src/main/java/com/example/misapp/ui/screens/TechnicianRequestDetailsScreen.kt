package com.example.misapp.ui.screens

import android.widget.Toast
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misapp.data.model.ServiceRequest
import com.example.misapp.viewmodel.ServiceRequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianRequestDetailsScreen(
    request: ServiceRequest,
    onBack: () -> Unit,
    viewModel: ServiceRequestViewModel = viewModel()
) {

    val context = LocalContext.current

    // ========================================
    // CURRENT REQUEST
    // ========================================

    var currentRequest by remember {
        mutableStateOf(request)
    }

    var isLoadingRequest by remember {
        mutableStateOf(false)
    }


    // ========================================
    // STATUS
    // ========================================

    var selectedStatus by remember {
        mutableStateOf(request.status)
    }

    var statusExpanded by remember {
        mutableStateOf(false)
    }

    var isUpdatingStatus by remember {
        mutableStateOf(false)
    }

    val statuses = listOf(
        "Pending",
        "Assigned",
        "In Progress",
        "On Hold",
        "Resolved",
        "Closed"
    )


    // ========================================
    // WORK NOTES
    // ========================================

    var workNotes by remember {
        mutableStateOf(request.workNotes)
    }

    var isSavingWorkNotes by remember {
        mutableStateOf(false)
    }


    // ========================================
    // RESOLUTION
    // ========================================

    var resolution by remember {
        mutableStateOf(request.resolution)
    }

    var isSavingResolution by remember {
        mutableStateOf(false)
    }


    // ========================================
    // LOAD LATEST REQUEST
    // ========================================

    fun loadLatestRequest() {

        isLoadingRequest = true

        viewModel.getRequestById(

            requestId = request.requestId,

            onSuccess = { latestRequest ->

                currentRequest = latestRequest

                selectedStatus =
                    latestRequest.status

                workNotes =
                    latestRequest.workNotes

                resolution =
                    latestRequest.resolution

                isLoadingRequest = false
            },

            onError = { error ->

                isLoadingRequest = false

                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }


    // ========================================
    // LOAD WHEN SCREEN OPENS
    // ========================================

    LaunchedEffect(request.requestId) {

        loadLatestRequest()
    }


    // ========================================
    // MAIN SCREEN
    // ========================================

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

        // ========================================
        // HEADER
        // ========================================

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

                style =
                    MaterialTheme.typography.headlineSmall,

                modifier =
                    Modifier.padding(
                        top = 12.dp,
                        start = 8.dp
                    )
            )
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // ========================================
        // LOADING
        // ========================================

        if (isLoadingRequest) {

            Text(
                text = "Loading latest request information...",

                style =
                    MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }


        // ========================================
        // REQUEST INFORMATION
        // ========================================

        Card(
            modifier = Modifier.fillMaxWidth(),

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

                Text(
                    text =
                        currentRequest.serviceCategory,

                    style =
                        MaterialTheme.typography.headlineSmall,

                    color =
                        MaterialTheme.colorScheme.primary
                )


                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                DetailItem(
                    label = "Request ID",
                    value = currentRequest.requestId
                )


                DetailItem(
                    label = "Client",
                    value = currentRequest.clientName
                )


                DetailItem(
                    label = "Email",
                    value = currentRequest.clientEmail
                )


                DetailItem(
                    label = "Priority",
                    value = currentRequest.priority
                )


                DetailItem(
                    label = "Current Status",
                    value = currentRequest.status
                )


                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )


                Text(
                    text = "Description",

                    style =
                        MaterialTheme.typography.titleMedium
                )


                Spacer(
                    modifier =
                        Modifier.height(4.dp)
                )


                Text(
                    text =
                        currentRequest.description,

                    style =
                        MaterialTheme.typography.bodyLarge
                )


                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                DetailItem(
                    label = "Assigned Technician",

                    value =
                        if (
                            currentRequest
                                .assignedTechnicianName
                                .isBlank()
                        ) {

                            "Not assigned"

                        } else {

                            currentRequest
                                .assignedTechnicianName
                        }
                )


                DetailItem(
                    label = "Deadline",

                    value =
                        if (
                            currentRequest.deadline
                                .isBlank()
                        ) {

                            "Not assigned"

                        } else {

                            currentRequest.deadline
                        }
                )
            }
        }


        Spacer(
            modifier =
                Modifier.height(18.dp)
        )


        // ========================================
        // UPDATE STATUS
        // ========================================

        Text(
            text = "Update Request Status",

            style =
                MaterialTheme.typography.titleLarge
        )


        Spacer(
            modifier =
                Modifier.height(8.dp)
        )


        ExposedDropdownMenuBox(

            expanded =
                statusExpanded,

            onExpandedChange = {

                statusExpanded =
                    !statusExpanded
            }
        ) {

            OutlinedTextField(

                value =
                    selectedStatus,

                onValueChange = {},

                readOnly = true,

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .menuAnchor(),

                label = {
                    Text("Status")
                },

                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                statusExpanded
                        )
                }
            )


            ExposedDropdownMenu(

                expanded =
                    statusExpanded,

                onDismissRequest = {

                    statusExpanded =
                        false
                }
            ) {

                statuses.forEach { status ->

                    DropdownMenuItem(

                        text = {
                            Text(status)
                        },

                        onClick = {

                            selectedStatus =
                                status

                            statusExpanded =
                                false
                        }
                    )
                }
            }
        }


        Spacer(
            modifier =
                Modifier.height(10.dp)
        )


        // ========================================
        // UPDATE STATUS BUTTON
        // ========================================

        Button(

            onClick = {

                if (isUpdatingStatus) {
                    return@Button
                }

                isUpdatingStatus = true

                viewModel.updateRequestStatus(

                    requestId =
                        currentRequest.requestId,

                    status =
                        selectedStatus,

                    onSuccess = {

                        isUpdatingStatus =
                            false

                        Toast.makeText(
                            context,
                            "Request status updated successfully!",
                            Toast.LENGTH_LONG
                        ).show()

                        loadLatestRequest()
                    },

                    onError = { error ->

                        isUpdatingStatus =
                            false

                        Toast.makeText(
                            context,
                            error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },

            enabled =
                !isUpdatingStatus,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
        ) {

            Text(
                text =
                    if (isUpdatingStatus) {
                        "UPDATING..."
                    } else {
                        "UPDATE STATUS"
                    }
            )
        }


        Spacer(
            modifier =
                Modifier.height(22.dp)
        )


        // ========================================
        // WORK NOTES
        // ========================================

        Text(
            text = "Work Notes",

            style =
                MaterialTheme.typography.titleLarge
        )


        Spacer(
            modifier =
                Modifier.height(4.dp)
        )


        Text(
            text =
                "Record the troubleshooting or work performed.",

            style =
                MaterialTheme.typography.bodyMedium,

            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )


        Spacer(
            modifier =
                Modifier.height(6.dp)
        )


        OutlinedTextField(

            value =
                workNotes,

            onValueChange = {
                workNotes = it
            },

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),

            label = {
                Text("Work Notes")
            },

            placeholder = {
                Text(
                    "Example: Checked network connection and replaced network cable."
                )
            }
        )


        Spacer(
            modifier =
                Modifier.height(8.dp)
        )


        // ========================================
        // SAVE WORK NOTES
        // ========================================

        Button(

            onClick = {

                if (workNotes.isBlank()) {

                    Toast.makeText(
                        context,
                        "Please enter work notes",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if (isSavingWorkNotes) {
                    return@Button
                }

                isSavingWorkNotes = true

                viewModel.updateWorkNotes(

                    requestId =
                        currentRequest.requestId,

                    workNotes =
                        workNotes,

                    onSuccess = {

                        isSavingWorkNotes =
                            false

                        Toast.makeText(
                            context,
                            "Work notes saved successfully!",
                            Toast.LENGTH_LONG
                        ).show()

                        loadLatestRequest()
                    },

                    onError = { error ->

                        isSavingWorkNotes =
                            false

                        Toast.makeText(
                            context,
                            error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },

            enabled =
                !isSavingWorkNotes,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
        ) {

            Text(
                text =
                    if (isSavingWorkNotes) {
                        "SAVING..."
                    } else {
                        "SAVE WORK NOTES"
                    }
            )
        }


        Spacer(
            modifier =
                Modifier.height(22.dp)
        )


        // ========================================
        // RESOLUTION
        // ========================================

        Text(
            text = "Resolution",

            style =
                MaterialTheme.typography.titleLarge
        )


        Spacer(
            modifier =
                Modifier.height(4.dp)
        )


        Text(
            text =
                "Describe how the issue was resolved.",

            style =
                MaterialTheme.typography.bodyMedium,

            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )


        Spacer(
            modifier =
                Modifier.height(6.dp)
        )


        OutlinedTextField(

            value =
                resolution,

            onValueChange = {
                resolution = it
            },

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),

            label = {
                Text("Resolution")
            },

            placeholder = {
                Text(
                    "Example: Reconfigured the network adapter and restored connectivity."
                )
            }
        )


        Spacer(
            modifier =
                Modifier.height(8.dp)
        )


        // ========================================
        // SAVE RESOLUTION
        // ========================================

        Button(

            onClick = {

                if (resolution.isBlank()) {

                    Toast.makeText(
                        context,
                        "Please enter a resolution",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if (isSavingResolution) {
                    return@Button
                }

                isSavingResolution = true

                viewModel.updateResolution(

                    requestId =
                        currentRequest.requestId,

                    resolution =
                        resolution,

                    onSuccess = {

                        isSavingResolution =
                            false

                        Toast.makeText(
                            context,
                            "Resolution saved successfully!",
                            Toast.LENGTH_LONG
                        ).show()

                        loadLatestRequest()
                    },

                    onError = { error ->

                        isSavingResolution =
                            false

                        Toast.makeText(
                            context,
                            error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            },

            enabled =
                !isSavingResolution,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
        ) {

            Text(
                text =
                    if (isSavingResolution) {
                        "SAVING..."
                    } else {
                        "SAVE RESOLUTION"
                    }
            )
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )
    }
}


// ========================================
// DETAIL ITEM
// ========================================

@Composable
private fun DetailItem(
    label: String,
    value: String
) {

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp
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
            modifier =
                Modifier.height(2.dp)
        )


        Text(
            text = value,

            style =
                MaterialTheme.typography.bodyLarge
        )
    }
}