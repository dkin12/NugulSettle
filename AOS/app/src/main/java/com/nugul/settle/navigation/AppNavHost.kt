package com.nugul.settle.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.nugul.settle.ui.screens.DetailScreen
import com.nugul.settle.ui.screens.HomeGroupScreen
import com.nugul.settle.ui.screens.MeetDegreeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    NavHost(
        navController = navController,
        startDestination = "group",
        modifier = modifier
    ) {
        composable("group") {
            HomeGroupScreen(navController, scrollBehavior)
        }
        composable(
            route = "detail/{groupId}",
            arguments = listOf(navArgument("groupId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("groupId")
            DetailScreen(groupIdx = itemId ?: "g1", navController, scrollBehavior)
        }
        composable(
            route = "detail/{groupId}/{meetIdx}",
            arguments = listOf(navArgument("meetIdx") { type = NavType.StringType },)
        ) {
            val meetIdx = it.arguments?.getString("meetIdx")
            if (meetIdx != null) {
                MeetDegreeScreen(meetIdx, navController, scrollBehavior)
            }
        }
    }
}
