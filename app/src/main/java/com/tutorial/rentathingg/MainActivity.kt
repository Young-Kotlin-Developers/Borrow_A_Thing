package com.tutorial.rentathingg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.tutorial.rentathingg.ui.theme.MainColor
import com.tutorial.rentathingg.ui.theme.RentAThinggTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)
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

                            composable("home") {

                                SignInScreen(navController)

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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


}