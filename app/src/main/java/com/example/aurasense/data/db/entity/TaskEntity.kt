package com.example.aurasense.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aurasense.data.db.TaskStatus

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val dueDate: Long?,
    val category: String,
    val status: TaskStatus,
    val isSynced: Boolean,
    val patientId: String,
    val photoPath: String? = null
)
