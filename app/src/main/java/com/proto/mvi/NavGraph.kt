package com.proto.mvi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.proto.mvi.ui.searchvcsuser.SearchAccountScreen


sealed class Screen(val route: String) {
    object SearchUser : Screen("SearchUser")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.SearchUser.route,
    ) {
        composable(
            Screen.SearchUser.route
        ) {
            SearchAccountScreen(
                navController = navController
            )
        }
    }
}