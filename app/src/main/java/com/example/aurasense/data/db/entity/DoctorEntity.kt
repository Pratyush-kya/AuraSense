package com.example.aurasense.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val specialty: String,
    val phone: String,
    val email: String?,
    val photoPath: String? = null
)