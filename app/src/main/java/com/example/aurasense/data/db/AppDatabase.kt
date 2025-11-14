package com.example.aurasense.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aurasense.data.db.dao.AppointmentDao
import com.example.aurasense.data.db.dao.DoctorDao
import com.example.aurasense.data.db.dao.EmergencyContactDao
import com.example.aurasense.data.db.dao.LocationDao
import com.example.aurasense.data.db.dao.PatientDao
import com.example.aurasense.data.db.dao.TaskDao
import com.example.aurasense.data.db.entity.AppointmentEntity
import com.example.aurasense.data.db.entity.DoctorEntity
import com.example.aurasense.data.db.entity.EmergencyContactEntity
import com.example.aurasense.data.db.entity.LocationEntity
import com.example.aurasense.data.db.entity.TaskEntity
import com.example.aurasense.data.db.models.Patient

@Database(
    entities = [TaskEntity::class, DoctorEntity::class, AppointmentEntity::class, EmergencyContactEntity::class, LocationEntity::class, Patient::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun doctorDao(): DoctorDao

    abstract fun appointmentDao(): AppointmentDao
    abstract fun emergencyContactDao(): EmergencyContactDao

    abstract fun locationDao(): LocationDao

    abstract fun patientDao(): PatientDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "aura_sense_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}