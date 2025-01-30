package com.example.l8.ui


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Screens(val route: String) {
    data object MojeOceny : Screens("MojeOceny")
    data object Edytuj : Screens("Edytuj")
    data object DodajNowy : Screens("DodajNowy")
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.MojeOceny.route,
        modifier = modifier
    ) {
        // Ekran "MojeOceny"
        composable(route = Screens.MojeOceny.route) {
            MojeOceny(navController, modifier)
        }

        // Ekran "DodajNowy"
        composable(route = Screens.DodajNowy.route) {
            DodajNowy(navController, modifier)
        }

        // Ekran "Edytuj" z dynamicznym parametrem ID
        composable(
            route = "edytuj/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // Pobranie ID z argumentów nawigacji
            val id = backStackEntry.arguments?.getInt("id")
            // Przekazanie ID do ekranu "Edytuj"
            Edytuj(navController, modifier, id ?: -1)
        }
    }
}
