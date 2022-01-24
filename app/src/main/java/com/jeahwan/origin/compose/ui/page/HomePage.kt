package com.jeahwan.origin.compose.ui.page

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun HomePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    Text("这是首页")
}