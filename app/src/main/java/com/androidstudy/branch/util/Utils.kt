package com.androidstudy.branch.util

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

internal class Utils {

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun getFormattedUpdateTime(dateToConvert: String): CharSequence {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            val time = sdf.parse(dateToConvert)!!.time
            val now = System.currentTimeMillis()
            return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
        }

    }
}