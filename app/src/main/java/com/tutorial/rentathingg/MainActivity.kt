package com.tutorial.rentathingg

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.tutorial.rentathingg.BottomNavBar.MainScreen
import com.tutorial.rentathingg.ui.theme.MainColor
import com.tutorial.rentathingg.ui.theme.RentAThinggTheme
import kotlinx.coroutines.runBlocking
import javax.xml.transform.Result

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
                           composable("Home") {
                                HomeScreen(navController)
                           }
                            composable("Details") {
                                DetailsScreen(navController)
                            }
                            composable("BottonNav") {
                                MainScreen(navController)
                            }
                            composable("Result") {
                                SerchScreen(navController,viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//
//}