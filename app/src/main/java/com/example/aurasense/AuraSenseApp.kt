package com.example.aurasense

import android.app.Application
import com.example.aurasense.data.db.AppDatabase
import com.example.aurasense.data.remote.TaskApiService
import com.example.aurasense.data.repository.PatientRepository
import com.example.aurasense.data.repository.TaskRepository
import com.example.aurasense.utils.ThemeManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuraSenseApp : Application() {

    private val database by lazy { AppDatabase.getDatabase(this) }

    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://69162bfda7a34288a27c9317.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApiService::class.java)
    }

    val repository by lazy { TaskRepository(database.taskDao(), database.doctorDao(), database.emergencyContactDao(), database.locationDao(), apiService) }
    val patientRepository by lazy { PatientRepository(database.patientDao()) }

    override fun onCreate() {
        super.onCreate()
        val themeManager = ThemeManager(this)
        themeManager.applyTheme(themeManager.getTheme())
    }
}