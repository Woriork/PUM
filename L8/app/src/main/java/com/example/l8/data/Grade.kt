package com.example.l8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val value: Double,
)