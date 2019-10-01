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

    fun setIsLoggedIn(isloggedin: Boolean) {
        val editor = settings.edit()
        editor.putBoolean("is_logged_in", isloggedin)
        editor.apply()
    }
}