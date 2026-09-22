package com.example.misapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.misapp.data.model.ServiceRequest
import com.example.misapp.data.model.UserProfile
import com.example.misapp.data.repository.ServiceRequestRepository

class ServiceRequestViewModel : ViewModel() {

    private val repository =
        ServiceRequestRepository()


    // ========================================
    // CREATE SERVICE REQUEST
    // ========================================

    fun createRequest(
        clientId: String,
        clientName: String,
        clientEmail: String,
        serviceCategory: String,
        description: String,
        priority: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        val request =
            ServiceRequest(

                clientId = clientId,

                clientName = clientName,

                clientEmail = clientEmail,

                serviceCategory = serviceCategory,

                description = description,

                priority = priority
            )

        repository.createServiceRequest(

            serviceRequest = request,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // GET REQUEST BY ID
    // ========================================

    fun getRequestById(
        requestId: String,
        onSuccess: (ServiceRequest) -> Unit,
        onError: (String) -> Unit
    ) {

        repository.getRequestById(

            requestId = requestId,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // GET CLIENT REQUESTS
    // ========================================

    fun getClientRequests(
        clientId: String,
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        repository.getClientRequests(

            clientId = clientId,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // GET ALL REQUESTS - ADMIN
    // ========================================

    fun getAllRequests(
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        repository.getAllRequests(

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // GET TECHNICIANS
    // ========================================

    fun getTechnicians(
        onSuccess: (List<UserProfile>) -> Unit,
        onError: (String) -> Unit
    ) {

        repository.getTechnicians(

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // ASSIGN TECHNICIAN
    // ========================================

    fun assignTechnician(
        requestId: String,
        technicianId: String,
        technicianName: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        repository.assignTechnician(

            requestId = requestId,

            technicianId = technicianId,

            technicianName = technicianName,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // UPDATE REQUEST STATUS
    // ========================================

    fun updateRequestStatus(
        requestId: String,
        status: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        repository.updateRequestStatus(

            requestId = requestId,

            status = status,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // UPDATE REQUEST DEADLINE
    // ========================================

    fun updateRequestDeadline(
        requestId: String,
        deadline: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        repository.updateRequestDeadline(

            requestId = requestId,

            deadline = deadline,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // UPDATE WORK NOTES
    // ========================================

    fun updateWorkNotes(
        requestId: String,
        workNotes: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        repository.updateWorkNotes(

            requestId = requestId,

            workNotes = workNotes,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // UPDATE RESOLUTION
    // ========================================

    fun updateResolution(
        requestId: String,
        resolution: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        repository.updateResolution(

            requestId = requestId,

            resolution = resolution,

            onSuccess = onSuccess,

            onError = onError
        )
    }


    // ========================================
    // GET TECHNICIAN ASSIGNED REQUESTS
    // ========================================

    fun getTechnicianRequests(
        technicianId: String,
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        repository.getTechnicianRequests(

            technicianId = technicianId,

            onSuccess = onSuccess,

            onError = onError
        )
    }
}