package com.jeahwan.origin.compose.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jeahwan.origin.compose.http.WebData
import com.jeahwan.origin.compose.ui.page.HomePage
import com.jeahwan.origin.compose.ui.page.LoginPage
import com.jeahwan.origin.compose.ui.page.MinePage
import com.jeahwan.origin.compose.ui.page.webview.WebViewPage
import com.jeahwan.origin.compose.ui.widgets.AppSnackBar
import com.jeahwan.origin.compose.ui.widgets.BottomNavBarView
import com.jeahwan.origin.compose.utils.fromJson
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            when (currentDestination?.route) {
                RouteName.HOME -> BottomNavBarView(navCtrl = navCtrl)
                RouteName.MINE -> BottomNavBarView(navCtrl = navCtrl)
            }
        },
        content = {
            var homeIndex = remember { 0 }
            var categoryIndex = remember { 0 }

            NavHost(
                modifier = Modifier.background(MaterialTheme.colors.background),
                navController = navCtrl,
                startDestination = RouteName.HOME
            ) {
                //首页
                composable(route = RouteName.HOME) {
                    HomePage(navCtrl, scaffoldState)
                }

                //我的
                composable(route = RouteName.MINE) {
                    MinePage(navCtrl, scaffoldState)
                }

                //登录
                composable(route = RouteName.LOGIN) {
                    LoginPage(navCtrl, scaffoldState)
                }

                //WebView
                composable(
                    route = RouteName.WEB_VIEW + "/{webData}",
                    arguments = listOf(navArgument("webData") { type = NavType.StringType })
                ) {
                    val args = it.arguments?.getString("webData")?.fromJson<WebData>()
                    if (args != null) {
                        WebViewPage(webData = args, navCtrl = navCtrl)
                    }
                }
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState
            ) { data ->
                println("actionLabel = ${data.actionLabel}")
                AppSnackBar(data = data)
            }
        }
    )
}