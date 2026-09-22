package com.example.misapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.misapp.data.model.UserProfile
import com.example.misapp.viewmodel.ServiceRequestViewModel

@Composable
fun TechniciansScreen(
    onBack: () -> Unit,
    viewModel: ServiceRequestViewModel = viewModel()
) {

    val context = LocalContext.current

    var technicians by remember {
        mutableStateOf<List<UserProfile>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }


    // ========================================
    // LOAD TECHNICIANS
    // ========================================

    fun loadTechnicians() {

        isLoading = true
        errorMessage = null

        viewModel.getTechnicians(

            onSuccess = { result ->

                technicians = result

                isLoading = false
            },

            onError = { error ->

                errorMessage = error

                isLoading = false

                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_LONG
                ).show()
            }
        )
    }


    LaunchedEffect(Unit) {

        loadTechnicians()
    }


    // ========================================
    // SCREEN
    // ========================================

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

            verticalAlignment =
                Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Back button clicked",
                            Toast.LENGTH_SHORT
                        ).show()

                        onBack()
                    }
                )
                {

                    Icon(
                        imageVector =
                            Icons.Default.ArrowBack,

                        contentDescription =
                            "Back"
                    )
                }

                Text(
                    text = "Technicians",

                    style =
                        MaterialTheme.typography.headlineSmall
                )
            }


            IconButton(
                onClick = {

                    loadTechnicians()
                }
            ) {

                Icon(
                    imageVector =
                        Icons.Default.Refresh,

                    contentDescription =
                        "Refresh"
                )
            }
        }


        // ========================================
        // CONTENT
        // ========================================

        when {

            isLoading -> {

                Column(
                    modifier =
                        Modifier.fillMaxSize(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement =
                        Arrangement.Center
                ) {

                    Text(
                        text =
                            "Loading technicians..."
                    )
                }
            }


            errorMessage != null -> {

                Column(
                    modifier =
                        Modifier.fillMaxSize(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement =
                        Arrangement.Center
                ) {

                    Text(
                        text =
                            errorMessage
                                ?: "Unable to load technicians",

                        color =
                            MaterialTheme.colorScheme.error
                    )
                }
            }


            technicians.isEmpty() -> {

                Column(
                    modifier =
                        Modifier.fillMaxSize(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement =
                        Arrangement.Center
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Person,

                        contentDescription =
                            "No Technicians",

                        tint =
                            MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text =
                            "No technicians found.",

                        style =
                            MaterialTheme.typography.titleMedium
                    )
                }
            }


            else -> {

                LazyColumn(

                    modifier =
                        Modifier.fillMaxSize(),

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    items(technicians) { technician ->

                        TechnicianCard(
                            technician =
                                technician
                        )
                    }
                }
            }
        }
    }
}


// ========================================
// TECHNICIAN CARD
// ========================================

@Composable
private fun TechnicianCard(
    technician: UserProfile
) {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

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

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Icon(

                    imageVector =
                        Icons.Default.Person,

                    contentDescription =
                        "Technician",

                    tint =
                        MaterialTheme.colorScheme.primary
                )


                Text(

                    text =
                        technician.fullName,

                    style =
                        MaterialTheme.typography.titleLarge,

                    modifier =
                        Modifier.padding(
                            start = 12.dp
                        )
                )
            }


            Text(

                text =
                    technician.email,

                style =
                    MaterialTheme.typography.bodyMedium,

                modifier =
                    Modifier.padding(
                        top = 12.dp
                    )
            )


            if (
                technician.phone.isNotBlank()
            ) {

                Text(

                    text =
                        technician.phone,

                    style =
                        MaterialTheme.typography.bodyMedium,

                    modifier =
                        Modifier.padding(
                            top = 4.dp
                        )
                )
            }


            Text(

                text =
                    "Role: Technician",

                style =
                    MaterialTheme.typography.labelLarge,

                color =
                    MaterialTheme.colorScheme.primary,

                modifier =
                    Modifier.padding(
                        top = 12.dp
                    )
            )
        }
    }
}