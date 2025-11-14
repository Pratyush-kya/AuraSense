package com.example.aurasense.ui.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.aurasense.data.repository.TaskRepository

class DoctorViewModel(repository: TaskRepository) : ViewModel() {
    val doctors = repository.getAllDoctors().asLiveData()
}
