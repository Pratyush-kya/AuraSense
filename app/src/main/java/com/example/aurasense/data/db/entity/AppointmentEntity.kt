package com.example.aurasense.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "appointments",
    foreignKeys = [ForeignKey(
        entity = DoctorEntity::class,
        parentColumns = ["id"],
        childColumns = ["doctorId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val doctorId: Long,
    val dateTime: Long,
    val notes: String?
)