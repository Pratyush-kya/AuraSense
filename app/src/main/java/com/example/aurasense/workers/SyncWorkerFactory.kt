package com.example.aurasense.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.aurasense.data.repository.TaskRepository

class SyncWorkerFactory(private val repository: TaskRepository) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            SyncWorker::class.java.name ->
                SyncWorker(appContext, workerParameters, repository)
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}
