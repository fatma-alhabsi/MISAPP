package com.example.misapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.misapp.data.model.ServiceRequest
import com.example.misapp.data.repository.ServiceRequestRepository

class TechnicianViewModel : ViewModel() {

    private val repository =
        ServiceRequestRepository()


    // --------------------------------
    // GET MY ASSIGNED REQUESTS
    // --------------------------------

    fun getMyAssignedRequests(
        technicianId: String,
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        repository.getTechnicianRequests(

            technicianId = technicianId,

            onSuccess = { requests ->

                onSuccess(
                    requests.sortedByDescending {
                        it.createdAt
                    }
                )
            },

            onError = { error ->

                onError(error)
            }
        )
    }
}