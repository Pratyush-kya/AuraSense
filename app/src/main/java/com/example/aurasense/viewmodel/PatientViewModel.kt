package com.example.aurasense.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.aurasense.data.db.models.Patient
import com.example.aurasense.data.repository.PatientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PatientViewModel(private val repository: PatientRepository) : ViewModel() {

    val allPatients: Flow<List<Patient>> = repository.allPatients

    fun insertPatient(patient: Patient) = viewModelScope.launch {
        repository.insertPatient(patient)
    }
}

class PatientViewModelFactory(private val repository: PatientRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}