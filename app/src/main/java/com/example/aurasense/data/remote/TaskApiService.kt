package com.example.aurasense.data.remote

import com.example.aurasense.data.remote.request.TaskDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): List<TaskDto>

    @POST("tasks")
    suspend fun createTask(@Body task: TaskDto): Response<Unit>

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body task: TaskDto): Response<Unit>

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: String): Response<Unit>
}
