package com.muhammadfaishalrizqipratama0094.cookit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muhammadfaishalrizqipratama0094.cookit.ui.screens.KategoriScreen
import com.muhammadfaishalrizqipratama0094.cookit.ui.screens.HomeScreen
import com.muhammadfaishalrizqipratama0094.cookit.ui.screens.ResepDetailScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Categories : Screen("categories")
    object ResepDetail : Screen("resep_detail/{resepId}") {
        fun createRoute(resepId: Int) = "resep_detail/$resepId"
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Categories.route) {
            KategoriScreen(navController = navController)
        }
        composable(
            route = Screen.ResepDetail.route,
            arguments = listOf(navArgument("resepId") { type = NavType.IntType })
        ) { backStackEntry ->
            val resepId = backStackEntry.arguments?.getInt("resepId") ?: 0
            ResepDetailScreen(
                resepId = resepId,
                navController = navController
            )
        }
    }
}