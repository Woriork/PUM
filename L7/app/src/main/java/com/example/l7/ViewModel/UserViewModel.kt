package com.example.l7.ViewModel

import androidx.lifecycle.ViewModel
import com.example.l7.Model.DataProvider

class UserViewModel : ViewModel() {
    val users = DataProvider.users
}