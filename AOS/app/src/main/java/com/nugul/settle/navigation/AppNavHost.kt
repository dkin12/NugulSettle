package com.nugul.settle.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.nugul.settle.ui.screens.DetailScreen
import com.nugul.settle.ui.screens.HomeGroupScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "group"
    ) {
        composable("group") {
            HomeGroupScreen(navController)
        }
        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("itemId")
            DetailScreen(itemId = itemId ?: "")
        }
    }
}
