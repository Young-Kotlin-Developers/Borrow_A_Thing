package com.tutorial.rentathingg.BottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "add",
        title = "Add",
        icon = Icons.Default.AddCircle
    )

    object Settings : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
