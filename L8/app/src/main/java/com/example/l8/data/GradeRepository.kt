package com.example.l8.data

import kotlinx.coroutines.flow.Flow

class GradesRepository(private val gD: GradeDao) {
    fun getGrades() = gD.getGrades()
    suspend fun clear() = gD.deleteAll()
    suspend fun add(grade: Grade) = gD.insert(grade)
    suspend fun update(grade: Grade) = gD.update(grade)
    suspend fun getById(id: Int) = gD.getGradeById(id)
    suspend fun delete(grade: Grade) = gD.delete(grade)
}