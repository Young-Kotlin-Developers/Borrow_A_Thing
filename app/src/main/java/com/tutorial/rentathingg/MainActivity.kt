package com.tutorial.rentathingg

import MoneyScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.tutorial.rentathingg.BottomNavBar.MainScreen
import com.tutorial.rentathingg.ui.theme.MainColor
import com.tutorial.rentathingg.ui.theme.RentAThinggTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ItemViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RentAThinggTheme {

                val navController = rememberNavController()
                ProvideWindowInsets {
                    Surface(
                        Modifier
                            .fillMaxSize(),
                        color = MainColor
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = "splash"
                        ) {
                            composable("splash") {
                                SplashScreen(navController)
                            }
                            composable("SigIn") {
                                SignInScreen(navController)
                            }
                            composable("Register") {
                                RegisterPage(navController)
                            }
                            composable("Money") {
                                MoneyScreen(navController)
                            }
                            composable("Home") {
                                ProfilScreen(navController, viewModel)
                            }
                           composable("Home") {
                                HomeScreen(navController)
                           }
                            composable("Result") {
                                SerchScreen(navController,viewModel)
                            }
                            composable("Offer") {
                                OfferCreatorScreen(navController)
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
                            composable("BottonNav") {
                                MainScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}