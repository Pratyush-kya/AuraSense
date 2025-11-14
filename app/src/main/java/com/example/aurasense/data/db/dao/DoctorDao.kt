package com.example.aurasense.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aurasense.data.db.entity.DoctorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctors")
    fun getAllDoctors(): Flow<List<DoctorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctor(doctor: DoctorEntity): Long

    @Delete
    suspend fun deleteDoctor(doctor: DoctorEntity)
}