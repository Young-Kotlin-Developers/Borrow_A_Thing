package com.tutorial.rentathingg.BottomNavBar

import MoneyScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tutorial.rentathingg.*


@Composable
fun BottomNavGraph(navController: NavHostController,viewModel: ItemViewModel) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Creator.route) {
            OfferCreatorScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfilScreen(navController, viewModel)
        }
        composable("Result") {
            SerchScreen(navController, viewModel)
        }
        composable(
            route = "Money/{TitleName}",
            arguments = listOf(
                navArgument("TitleName") { type = NavType.IntType },
            )
        ){ backStackEntry ->
            MoneyScreen(
                navController,
                viewModel,
                backStackEntry.arguments?.getInt("titleName")
            )
        }
        composable(
            route = "Details/{colorName}",
            arguments = listOf(
                navArgument("colorName") { type = NavType.IntType },
            )
        ){ backStackEntry ->
            DetailsScreen(
                navController,
                viewModel,
                backStackEntry.arguments?.getInt("colorName")
            )
        }
        composable("Offer") {
            OfferCreatorScreen(navController)
        }
        composable("SigIn") {
            SignInScreen(navController)
        }
        composable("Profile") {
            ProfilScreen(navController, viewModel)
        }
    }
}