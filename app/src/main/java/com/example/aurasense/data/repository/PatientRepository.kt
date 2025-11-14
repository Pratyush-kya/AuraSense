package com.example.aurasense.data.repository

import com.example.aurasense.data.db.dao.PatientDao
import com.example.aurasense.data.db.models.Patient
import kotlinx.coroutines.flow.Flow

class PatientRepository(private val patientDao: PatientDao) {

    val allPatients: Flow<List<Patient>> = patientDao.getAllPatients()

    suspend fun insertPatient(patient: Patient) {
        patientDao.insertPatient(patient)
    }
}
