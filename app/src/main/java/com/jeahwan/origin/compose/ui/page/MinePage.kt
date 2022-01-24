package com.jeahwan.origin.compose.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jeahwan.origin.compose.ui.page.common.RouteName
import com.jeahwan.origin.compose.ui.theme.AppTheme
import com.jeahwan.origin.compose.ui.theme.BottomNavBarHeight
import com.jeahwan.origin.compose.ui.widgets.AppToolsBar
import com.jeahwan.origin.compose.ui.widgets.EmptyView
import com.jeahwan.origin.compose.utils.RouteUtils

@Composable
fun MinePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    DisposableEffect(Unit) {
        Log.i("debug", "onStart")
        onDispose {
            Log.i("debug", "onDispose")
        }
    }

    Column(
        modifier = Modifier
            .padding(bottom = BottomNavBarHeight)
            .fillMaxSize()
            .background(color = AppTheme.colors.mainColor)
    ) {
        if (false) {
            //登录布局
        } else {
            AppToolsBar(title = "我的")
            EmptyView(
                tips = "点击登录",
                imageVector = Icons.Default.Face
            ) {
                RouteUtils.navTo(navCtrl, RouteName.LOGIN)
            }
        }
    }
}