
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misapp.viewmodel.ServiceRequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceRequestScreen(
    clientId: String,
    clientName: String,
    clientEmail: String,
    onBack: () -> Unit,
    onRequestSubmitted: () -> Unit,
    viewModel: ServiceRequestViewModel = viewModel()
) {

    val context = LocalContext.current

    var serviceCategory by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var priority by remember {
        mutableStateOf("Normal")
    }

    var categoryExpanded by remember {
        mutableStateOf(false)
    }

    var priorityExpanded by remember {
        mutableStateOf(false)
    }

    var categoryError by remember {
        mutableStateOf<String?>(null)
    }

    var descriptionError by remember {
        mutableStateOf<String?>(null)
    }

    var isSubmitting by remember {
        mutableStateOf(false)
    }

    val categories = listOf(
        "Computer Support",
        "Network Support",
        "Printer / Scanner",
        "Email Support",
        "Software Installation",
        "Wi-Fi / Internet",
        "IT Security",
        "Other"
    )

    val priorities = listOf(
        "Low",
        "Normal",
        "High",
        "Urgent"
    )

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
                text = "Request IT Service",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 8.dp
                )
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Service Category",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = categoryExpanded,
            onExpandedChange = {
                categoryExpanded = !categoryExpanded
            }
        ) {

            OutlinedTextField(
                value = serviceCategory,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = {
                    Text("Select service")
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = categoryExpanded
                    )
                },
                isError = categoryError != null
            )

            ExposedDropdownMenu(
                expanded = categoryExpanded,
                onDismissRequest = {
                    categoryExpanded = false
                }
            ) {

                categories.forEach { category ->

                    DropdownMenuItem(
                        text = {
                            Text(category)
                        },
                        onClick = {

                            serviceCategory = category

                            categoryError = null

                            categoryExpanded = false
                        }
                    )
                }
            }
        }

        categoryError?.let {

            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Describe Your Problem",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = {

                description = it

                descriptionError = null
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            label = {
                Text("Problem description")
            },
            placeholder = {
                Text(
                    "Example: My computer cannot connect to the network..."
                )
            },
            isError = descriptionError != null
        )

        descriptionError?.let {

            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Priority",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = priorityExpanded,
            onExpandedChange = {
                priorityExpanded = !priorityExpanded
            }
        ) {

            OutlinedTextField(
                value = priority,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                label = {
                    Text("Priority")
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = priorityExpanded
                    )
                }
            )

            ExposedDropdownMenu(
                expanded = priorityExpanded,
                onDismissRequest = {
                    priorityExpanded = false
                }
            ) {

                priorities.forEach { selectedPriority ->

                    DropdownMenuItem(
                        text = {
                            Text(selectedPriority)
                        },
                        onClick = {

                            priority = selectedPriority

                            priorityExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        Button(
            onClick = {

                categoryError = null
                descriptionError = null

                if (serviceCategory.isBlank()) {

                    categoryError =
                        "Please select a service category"
                }

                if (description.isBlank()) {

                    descriptionError =
                        "Please describe your problem"

                } else if (description.length < 10) {

                    descriptionError =
                        "Please provide more details"
                }

                if (
                    categoryError == null &&
                    descriptionError == null &&
                    !isSubmitting
                ) {

                    isSubmitting = true

                    viewModel.createRequest(

                        clientId = clientId,

                        clientName = clientName,

                        clientEmail = clientEmail,

                        serviceCategory = serviceCategory,

                        description = description.trim(),

                        priority = priority,

                        onSuccess = {

                            Toast.makeText(
                                context,
                                "Service request submitted successfully!",
                                Toast.LENGTH_LONG
                            ).show()

                            onRequestSubmitted()
                        },

                        onError = { errorMessage ->

                            isSubmitting = false

                            Toast.makeText(
                                context,
                                errorMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            },

            enabled = !isSubmitting,

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {

            Text(
                text = if (isSubmitting) {
                    "SUBMITTING..."
                } else {
                    "SUBMIT REQUEST"
                }
            )
        }
    }
}