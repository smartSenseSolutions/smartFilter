package com.ss.smartfilterlib.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.ss.smartfilterlib.data.RadioGroupData


fun Context.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    try {
        if (isOnMainThread()) {
            Toast.makeText(applicationContext, msg, length).show()
        } else {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, msg, length).show()
            }
        }
    } catch (_: Exception) {}

}
fun isOnMainThread() = Looper.myLooper() == Looper.getMainLooper()









