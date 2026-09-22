package com.example.misapp.data.model

data class ServiceRequest(
    val requestId: String = "",
    val clientId: String = "",
    val clientName: String = "",
    val clientEmail: String = "",
    val serviceCategory: String = "",
    val description: String = "",
    val priority: String = "Normal",
    val status: String = "Pending",
    val assignedTechnicianId: String = "",
    val assignedTechnicianName: String = "",
    val deadline: String = "",
    val workNotes: String = "",
    val resolution: String = "",
    val createdAt: Long = System.currentTimeMillis()
)