package com.example.l8.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Grade::class], version = 1, exportSchema = false)
abstract class GradeDatabase : RoomDatabase() {
    abstract fun gradeDao(): GradeDao

    companion object {
        @Volatile
        private var Instance: GradeDatabase? = null

        fun getDatabase(context: Context): GradeDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GradeDatabase::class.java, "grade_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}