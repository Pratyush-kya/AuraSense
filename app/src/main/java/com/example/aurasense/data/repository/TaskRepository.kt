package com.example.aurasense.data.repository

import com.example.aurasense.data.db.dao.DoctorDao
import com.example.aurasense.data.db.dao.EmergencyContactDao
import com.example.aurasense.data.db.dao.LocationDao
import com.example.aurasense.data.db.dao.TaskDao
import com.example.aurasense.data.db.entity.DoctorEntity
import com.example.aurasense.data.db.entity.EmergencyContactEntity
import com.example.aurasense.data.db.entity.LocationEntity
import com.example.aurasense.data.db.entity.TaskEntity
import com.example.aurasense.data.remote.TaskApiService
import com.example.aurasense.data.remote.request.toDto
import com.example.aurasense.data.remote.request.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class TaskRepository(
    private val taskDao: TaskDao,
    private val doctorDao: DoctorDao,
    private val emergencyContactDao: EmergencyContactDao,
    private val locationDao: LocationDao,
    private val apiService: TaskApiService
) {
    fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasks()

    fun getTasksForPatient(patientId: String): Flow<List<TaskEntity>> = taskDao.getTasksForPatient(patientId)

    fun getAllDoctors(): Flow<List<DoctorEntity>> = doctorDao.getAllDoctors()

    fun getAllEmergencyContacts(): Flow<List<EmergencyContactEntity>> = emergencyContactDao.getAllContacts()

    fun getEmergencyContact(contactId: Long): Flow<EmergencyContactEntity> = emergencyContactDao.getContactById(contactId)

    suspend fun insertDoctor(doctor: DoctorEntity) {
        doctorDao.insertDoctor(doctor)
    }

    suspend fun insertEmergencyContact(contact: EmergencyContactEntity) {
        emergencyContactDao.insertContact(contact)
    }

    suspend fun updateEmergencyContact(contact: EmergencyContactEntity) {
        emergencyContactDao.updateContact(contact)
    }

    suspend fun insertLocation(location: LocationEntity) {
        locationDao.insertLocation(location)
    }

    suspend fun addOrUpdateTask(task: TaskEntity) {
        taskDao.insertTask(task)
        try {
            apiService.updateTask(task.id, task.toDto())
        } catch (e: Exception) {

            taskDao.insertTask(task.copy(isSynced = false))
        }
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
        try {
            apiService.deleteTask(task.id)
        } catch (e: Exception) {

        }
    }

    suspend fun syncTasks() {
        val localTasks = taskDao.getAllTasks().first()
        val remoteTasks = apiService.getTasks().map { it.toEntity() }
    }
}