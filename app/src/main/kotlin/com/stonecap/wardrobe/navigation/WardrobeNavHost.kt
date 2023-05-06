package com.stonecap.wardrobe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stonecap.wardrobe.feature.dashboard.navigation.dashboardNavigationRoute
import com.stonecap.wardrobe.feature.dashboard.navigation.dashboardScreen
import com.stonecap.wardrobe.feature.itemdetails.naviagation.itemDetailsScreen

@Composable
fun WardrobeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = dashboardNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        dashboardScreen()
        itemDetailsScreen()

    }
}
