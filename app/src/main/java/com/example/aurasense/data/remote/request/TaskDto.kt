package com.example.aurasense.data.remote.request

import com.example.aurasense.data.db.TaskStatus
import com.example.aurasense.data.db.entity.TaskEntity

data class TaskDto(
    val id: String,
    val title: String,
    val description: String,
    val dueDate: Long,
    val category: String,
    val isCompleted: Boolean,
    val patientId: String
)

fun TaskDto.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate,
        category = category,
        status = if (isCompleted) TaskStatus.COMPLETED else TaskStatus.PENDING,
        isSynced = true,
        patientId = patientId
    )
}

fun TaskEntity.toDto(): TaskDto {
    return TaskDto(
        id = id,
        title = title,
        description = description ?: "",
        dueDate = dueDate ?: 0,
        category = category,
        isCompleted = status == TaskStatus.COMPLETED,
        patientId = patientId
    )
}
