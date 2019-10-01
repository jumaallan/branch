package com.androidstudy.branch

import android.app.Application
import androidx.annotation.Nullable
import com.androidstudy.branch.settings.Settings
import org.jetbrains.annotations.NotNull
import timber.log.Timber

class Branch : Application() {

    lateinit var settings: Settings

    override fun onCreate() {
        super.onCreate()

        settings = Settings(applicationContext)

        initTimber()
    }

    private fun initTimber() {
        Timber.plant(object : Timber.DebugTree() {
            @Nullable
            override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }

}