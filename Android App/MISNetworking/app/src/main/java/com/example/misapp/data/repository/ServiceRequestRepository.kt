package com.example.misapp.data.repository

import com.example.misapp.data.model.ServiceRequest
import com.example.misapp.data.model.UserProfile
import com.google.firebase.firestore.FirebaseFirestore

class ServiceRequestRepository {

    private val firestore =
        FirebaseFirestore.getInstance()

    private val requestsCollection =
        firestore.collection("serviceRequests")

    private val usersCollection =
        firestore.collection("users")


    // ========================================
    // CREATE SERVICE REQUEST
    // ========================================

    fun createServiceRequest(
        serviceRequest: ServiceRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        requestsCollection
            .document(serviceRequest.requestId)
            .set(serviceRequest)
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to submit service request"
                )
            }
    }


    // ========================================
    // GET REQUEST BY ID
    // ========================================

    fun getRequestById(
        requestId: String,
        onSuccess: (ServiceRequest) -> Unit,
        onError: (String) -> Unit
    ) {

        requestsCollection
            .document(requestId)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    val request =
                        document.toObject(
                            ServiceRequest::class.java
                        )

                    if (request != null) {

                        onSuccess(request)

                    } else {

                        onError(
                            "Unable to read service request"
                        )
                    }

                } else {

                    onError(
                        "Service request not found"
                    )
                }
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to load service request"
                )
            }
    }


    // ========================================
    // GET CLIENT REQUESTS
    // ========================================

    fun getClientRequests(
        clientId: String,
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        requestsCollection
            .whereEqualTo(
                "clientId",
                clientId
            )
            .get()
            .addOnSuccessListener { snapshot ->

                val requests =
                    snapshot.documents.mapNotNull { document ->

                        document.toObject(
                            ServiceRequest::class.java
                        )
                    }

                onSuccess(requests)
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to load service requests"
                )
            }
    }


    // ========================================
    // GET ALL REQUESTS - ADMIN
    // ========================================

    fun getAllRequests(
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        requestsCollection
            .get()
            .addOnSuccessListener { snapshot ->

                val requests =
                    snapshot.documents.mapNotNull { document ->

                        document.toObject(
                            ServiceRequest::class.java
                        )
                    }

                onSuccess(requests)
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to load all service requests"
                )
            }
    }


    // ========================================
    // GET TECHNICIAN ASSIGNED REQUESTS
    // ========================================

    fun getTechnicianRequests(
        technicianId: String,
        onSuccess: (List<ServiceRequest>) -> Unit,
        onError: (String) -> Unit
    ) {

        requestsCollection
            .whereEqualTo(
                "assignedTechnicianId",
                technicianId
            )
            .get()
            .addOnSuccessListener { snapshot ->

                val requests =
                    snapshot.documents.mapNotNull { document ->

                        document.toObject(
                            ServiceRequest::class.java
                        )
                    }

                onSuccess(requests)
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to load assigned requests"
                )
            }
    }


    // ========================================
    // GET TECHNICIANS
    // ========================================

    fun getTechnicians(
        onSuccess: (List<UserProfile>) -> Unit,
        onError: (String) -> Unit
    ) {

        usersCollection
            .whereEqualTo(
                "role",
                "technician"
            )
            .get()
            .addOnSuccessListener { snapshot ->

                val technicians =
                    snapshot.documents.mapNotNull { document ->

                        document.toObject(
                            UserProfile::class.java
                        )
                    }

                onSuccess(technicians)
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to load technicians"
                )
            }
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

        requestsCollection
            .document(requestId)
            .update(
                mapOf(
                    "assignedTechnicianId"
                            to technicianId,

                    "assignedTechnicianName"
                            to technicianName
                )
            )
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to assign technician"
                )
            }
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

        requestsCollection
            .document(requestId)
            .update(
                "status",
                status
            )
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to update request status"
                )
            }
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

        requestsCollection
            .document(requestId)
            .update(
                "deadline",
                deadline
            )
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to update request deadline"
                )
            }
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

        requestsCollection
            .document(requestId)
            .update(
                "workNotes",
                workNotes
            )
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to save work notes"
                )
            }
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

        requestsCollection
            .document(requestId)
            .update(
                "resolution",
                resolution
            )
            .addOnSuccessListener {

                onSuccess()
            }
            .addOnFailureListener { exception ->

                onError(
                    exception.message
                        ?: "Failed to save resolution"
                )
            }
    }
}