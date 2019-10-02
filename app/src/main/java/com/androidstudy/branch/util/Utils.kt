package com.androidstudy.branch.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

internal class Utils {

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun getFormattedUpdateTime(dateToConvert: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
            val date = dateFormat.parse(dateToConvert)
            return String.format(date.toString())
        }
    }
}