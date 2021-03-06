package com.jeahwan.origin.compose.ui.page.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavRoute(
    var routeName: String,
    var label: String,
    var icon: ImageVector
) {
    object Home : BottomNavRoute(RouteName.HOME, "้ฆ้กต", Icons.Default.Home)
    object Profile : BottomNavRoute(RouteName.MINE, "ๆ็", Icons.Default.Person)
}