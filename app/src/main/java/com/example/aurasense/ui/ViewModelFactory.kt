package com.example.aurasense.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aurasense.data.repository.TaskRepository
import com.example.aurasense.ui.contacts.EmergencyContactViewModel
import com.example.aurasense.ui.doctors.DoctorViewModel
import com.example.aurasense.ui.tasks.TaskViewModel

class ViewModelFactory(private val repository: TaskRepository, private val patientId: String? = null) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository, patientId) as T
        }
        if (modelClass.isAssignableFrom(DoctorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DoctorViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(EmergencyContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmergencyContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}