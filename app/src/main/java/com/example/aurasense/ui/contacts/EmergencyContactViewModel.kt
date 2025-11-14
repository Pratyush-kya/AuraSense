package com.example.aurasense.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.aurasense.data.db.entity.EmergencyContactEntity
import com.example.aurasense.data.repository.TaskRepository
import kotlinx.coroutines.launch

class EmergencyContactViewModel(private val repository: TaskRepository) : ViewModel() {
    val contacts = repository.getAllEmergencyContacts().asLiveData()

    fun getContact(id: Long) = repository.getEmergencyContact(id).asLiveData()

    fun insert(contact: EmergencyContactEntity) = viewModelScope.launch {
        repository.insertEmergencyContact(contact)
    }

    fun update(contact: EmergencyContactEntity) = viewModelScope.launch {
        repository.updateEmergencyContact(contact)
    }
}
