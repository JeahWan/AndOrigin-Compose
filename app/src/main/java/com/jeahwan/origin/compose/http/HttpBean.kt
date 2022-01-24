package com.jeahwan.origin.compose.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class BaseBean<T>(
        var data: T?,
        var code: Int,
        var message: String
)

@Parcelize
data class WebData(
        var title: String?,
        var url: String
) : Parcelable

@Parcelize
data class UserInfo(
        var id: Int,
        var admin: Boolean,
        var chapterTops: MutableList<Int>,
        var coinCount: Int,
        var collectIds: MutableList<Int>,
        var email: String,
        var icon: String,
        var nickname: String,
        var password: String,
        var token: String,
        var type: Int,
        var username: String,
) : Parcelable