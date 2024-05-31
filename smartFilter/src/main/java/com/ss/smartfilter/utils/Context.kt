package com.ss.smartfilter.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast


/**
 * created by Mala Ruparel ON 08/05/24
 */
fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, message, length).show()
    }
}









