package com.stonecap.wardrobe.feature.itemdetails.naviagation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.stonecap.wardrobe.feature.itemdetails.ItemDetailsRoute

const val navRouteItemDetails = "item_details_route"

fun NavController.navigateToItemDetails(navOptions: NavOptions? = null) {
    this.navigate(navRouteItemDetails, navOptions)
}

fun NavGraphBuilder.itemDetailsScreen() {
    composable(route = navRouteItemDetails) {
        ItemDetailsRoute()
    }
}
