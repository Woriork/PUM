package com.example.l7.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.l7.ViewModel.UserViewModel

@Composable
fun AppNavigation(viewModel: UserViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") { ViewList(viewModel, navController) }
        composable("details/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            ViewDetails(viewModel, navController, userId)
        }
    }
}