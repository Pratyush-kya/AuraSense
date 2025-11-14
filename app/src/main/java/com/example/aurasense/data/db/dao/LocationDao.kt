package com.example.aurasense.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.aurasense.data.db.entity.LocationEntity

@Dao
interface LocationDao {
    @Insert
    suspend fun insertLocation(location: LocationEntity)
}
