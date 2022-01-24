package com.jeahwan.origin.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.jeahwan.origin.compose.ui.page.SplashPage
import com.jeahwan.origin.compose.ui.page.common.AppScaffold
import com.jeahwan.origin.compose.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeEntry()
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
private fun HomeEntry() {
    //是否闪屏页
    var isSplash by remember { mutableStateOf(true) }
    AppTheme {
        if (isSplash) {
            SplashPage { isSplash = false }
        } else {
            AppScaffold()
        }
    }
}