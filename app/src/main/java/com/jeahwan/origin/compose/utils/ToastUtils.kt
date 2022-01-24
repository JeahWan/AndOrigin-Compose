package com.jeahwan.origin.compose.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import com.jeahwan.origin.compose.App

private var mToast: Toast? = null

/**
 * 显示时间较短的吐司
 *
 * @param text String，显示的内容
 */
fun showToast(text: String?) {
    showToast(context = App.CONTEXT, text = text)
}

fun showToast(context: Context = App.CONTEXT, text: String?) {
    if (TextUtils.isEmpty(text)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, text, Toast.LENGTH_SHORT)
    } else {
        Handler(context.mainLooper).post { showToast(context, text, Toast.LENGTH_SHORT) }
    }
}

/**
 * 显示时间较短的吐司
 *
 * @param resId int，显示内容的字符串索引
 */
fun showToast(context: Context = App.CONTEXT, resId: Int) {
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, resId, Toast.LENGTH_SHORT)
    } else {
        Handler(context.mainLooper).post { showToast(context, resId, Toast.LENGTH_SHORT) }
    }
}

/**
 * 显示时间较长的吐司
 *
 * @param text String，显示的内容
 */
fun showLongToast(context: Context? = App.CONTEXT, text: String?) {
    if (context == null || TextUtils.isEmpty(text)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, text, Toast.LENGTH_LONG)
    } else {
        Handler(context.mainLooper).post { showToast(context, text, Toast.LENGTH_LONG) }
    }
}

/**
 * 显示时间较长的吐司
 *
 * @param resId int，显示内容的字符串索引
 */
fun showLongToast(context: Context? = App.CONTEXT, resId: Int) {
    if (context == null) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, resId, Toast.LENGTH_LONG)
    } else {
        Handler(context.mainLooper).post { showToast(context, resId, Toast.LENGTH_LONG) }
    }
}

private fun showToast(context: Context? = App.CONTEXT, text: String?, duration: Int) {
    if (TextUtils.isEmpty(text)) return
    cancelToast()
    if (mToast == null) {
        mToast = Toast.makeText(context, null as CharSequence?, duration)
    }
    mToast?.apply {
        setText(text)
        this.duration = duration
        show()
    }
}

fun showToast(context: Context? = App.CONTEXT, res: Int, duration: Int) {
    cancelToast()
    if (mToast == null) {
        mToast = Toast.makeText(context, res, duration)
    } else {
        mToast?.setText(res)
        mToast?.duration = duration
    }
    mToast?.show()
}

fun cancelToast() {
    //会导致不能重复弹出
//    mToast?.cancel()
}