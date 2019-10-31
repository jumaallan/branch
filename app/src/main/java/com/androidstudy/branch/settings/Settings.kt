package com.androidstudy.branch.settings

import android.content.Context
import android.content.SharedPreferences

class Settings(
    context: Context
) {

    private var settings: SharedPreferences =
        context.getSharedPreferences("branch_settings", Context.MODE_PRIVATE)

    fun getBranchAuthToken(): String? {
        return settings.getString("branch_auth_token", "")
    }

    fun setBranchAuthToken(branch_auth_token: String) {
        val editor = settings.edit()
        editor.putString("branch_auth_token", branch_auth_token)
        editor.apply()
    }

    fun isLoggedIn(): Boolean? {
        return settings.getBoolean("is_logged_in", false)
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        val editor = settings.edit()
        editor.putBoolean("is_logged_in", isLoggedIn)
        editor.apply()
    }

    fun isFirstTime(): Boolean? {
        return settings.getBoolean("is_first_time", false)
    }

    fun setIsFirstTime(isFirstTime: Boolean) {
        val editor = settings.edit()
        editor.putBoolean("is_first_time", isFirstTime)
        editor.apply()
    }
}