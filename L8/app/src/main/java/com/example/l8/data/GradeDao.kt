package com.example.l8.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDao {
    @Query("SELECT * FROM grades ORDER BY id ASC, name ASC")
    fun getGrades(): Flow<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Grade)

    @Query("SELECT * FROM grades WHERE name = :name")
    fun getGradeByName(name: String): List<Grade>

    @Query("SELECT * FROM grades WHERE id = :id")
    suspend fun getGradeById(id: Int): Grade

    @Query("DELETE FROM grades")
    suspend fun deleteAll()

    @Update
    suspend fun update(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)
}