package com.tutorial.rentathingg.BottomNavBar

import ProfileScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tutorial.rentathingg.DetailsScreen
import com.tutorial.rentathingg.HomeScreen
import com.tutorial.rentathingg.OfferCreatorScreen



@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            OfferCreatorScreen(navController)
        }
        composable(route = BottomBarScreen.Settings.route) {
            DetailsScreen(navController)
        }
    }
}