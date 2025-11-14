package com.example.aurasense.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aurasense.data.db.TaskStatus
import com.example.aurasense.data.db.entity.TaskEntity
import com.example.aurasense.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class TaskViewModel(
    private val repository: TaskRepository,
    private val patientId: String?
) : ViewModel() {
    val tasks: StateFlow<List<TaskEntity>> = (if (patientId == null) {
        repository.getAllTasks()
    } else {
        repository.getTasksForPatient(patientId)
    }).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _filterCategory = MutableStateFlow<String?>(null)
    val filteredTasks: StateFlow<List<TaskEntity>> = combine(tasks, _filterCategory) { tasks, category ->
        category?.let { tasks.filter { it.category == category } } ?: tasks
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setCategoryFilter(category: String?) {
        _filterCategory.value = category
    }

    suspend fun addOrUpdateTask(task: TaskEntity) {
        repository.addOrUpdateTask(task)
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    suspend fun insertTask(title: String, description: String?, imagePath: String?) {
        patientId?.let {
            val newTask = TaskEntity(
                id = UUID.randomUUID().toString(),
                title = title,
                description = description,
                dueDate = null,
                category = "General",
                status = TaskStatus.PENDING,
                isSynced = false,
                patientId = it,
                photoPath = imagePath
            )
            addOrUpdateTask(newTask)
        }
    }
}
