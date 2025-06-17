package br.com.cyrojrdev.ui.screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    onChangeIndexNavBarNavItem: (Int) -> Unit,
    contentPaddingValues: PaddingValues,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("Home") {}
        composable("Cities") {}
        composable("Settings") { }
    }
}
