package com.jeahwan.origin.compose.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jeahwan.origin.compose.http.ApiCall
import com.jeahwan.origin.compose.http.HttpResult
import com.jeahwan.origin.compose.ui.theme.AppTheme
import com.jeahwan.origin.compose.ui.theme.ToolBarHeight
import com.jeahwan.origin.compose.ui.widgets.AppButton
import com.jeahwan.origin.compose.ui.widgets.LargeTitle
import com.jeahwan.origin.compose.ui.widgets.LoginEditView
import com.jeahwan.origin.compose.utils.RouteUtils.back
import com.jeahwan.origin.compose.utils.showToast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun LoginPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var account by remember { mutableStateOf("test") }
    var password by remember { mutableStateOf("111111") }

    LaunchedEffect(Unit) {
//        viewModel.viewEvents.collect {
//            if (it is LoginViewEvent.PopBack) {
//                navCtrl.popBackStack()
//            } else if (it is LoginViewEvent.ErrorMessage) {
//                popupSnackBar(coroutineState, scaffoldState, label = SNACK_ERROR, it.message)
//            }
//        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.themeUi)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        keyboardController?.hide()
                    }
                )
            },
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .fillMaxWidth()
                    .height(ToolBarHeight)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = AppTheme.colors.mainColor,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.CenterStart)
                        .clickable { navCtrl.back() }
                )
            }
        }
        item {
            Box(Modifier.fillMaxWidth()) {
                LargeTitle(
                    title = "登录",
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .align(Alignment.Center),
                    color = AppTheme.colors.mainColor
                )
            }
        }
        item {
            LoginEditView(
                text = account,
                labelText = "账号",
                hintText = "请输入用户名",
                onValueChanged = { account = it },
                onDeleteClick = { account = "" }
            )
        }
        item {
            LoginEditView(
                text = password,
                labelText = "密码",
                hintText = "请输入密码",
                onValueChanged = { password = it },
                onDeleteClick = { password = "" },
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                isPassword = true
            )
        }
        item {
            AppButton(
                text = "登录",
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                keyboardController?.hide()
                login(navCtrl, account, password)
            }
        }
    }
}

private fun login(navCtrl: NavHostController, account: String, password: String) {
    GlobalScope.launch {
        flow {
            emit(ApiCall.retrofit.login(account.trim(), password.trim()))
        }.map {
            if (it.code == 0) {
                if (it.data != null) {
                    HttpResult.Success(it.data!!)
                } else {
                    throw Exception("the result of remote's request is null")
                }
            } else {
                throw Exception(it.message)
            }
        }.onEach {
            //登录成功
            showToast("登录成功")
            navCtrl.popBackStack()
        }.catch {
            showToast(it.message)
        }.collect()
    }
}