package com.jeahwan.origin.compose.http

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 网络请求接口
 * 注意：接口前无需加斜杠
 */
interface HttpService {
    //登录
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
            @Field("username") userName: String,
            @Field("password") password: String,
    ): BaseBean<UserInfo>
}