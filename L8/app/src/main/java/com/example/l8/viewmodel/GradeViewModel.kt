package com.example.l8.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.l8.data.Grade
import com.example.l8.data.GradeDatabase
import com.example.l8.data.GradesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : ViewModel() {

    private val repository: GradesRepository
    private val _gradesState = MutableStateFlow<List<Grade>>(emptyList())
    val gradesState: StateFlow<List<Grade>>
        get() = _gradesState

    private val _grade = MutableLiveData<Grade>()
    val grade: LiveData<Grade> get() = _grade

    init {
        val db = GradeDatabase.getDatabase(application)
        val dao = db.gradeDao()
        repository = GradesRepository(dao)

        fetchGrades()
    }

    private fun fetchGrades() {
        viewModelScope.launch {
            repository.getGrades().collect { grades ->
                _gradesState.value = grades
            }
        }
    }

    fun clearGrade() {
        viewModelScope.launch {
            repository.clear()
        }
    }

    fun delete(grade: Grade) {
        viewModelScope.launch {
            repository.delete(grade)
        }
    }

    fun addGrade(grade: Grade) {
        viewModelScope.launch {
            repository.add(grade)
        }
    }

    fun getGradeById(id: Int) {
        viewModelScope.launch {
            val result = repository.getById(id)
            _grade.postValue(result)
        }
    }

    fun update(grade: Grade) {
        viewModelScope.launch {
            repository.update(grade)
        }
    }
}