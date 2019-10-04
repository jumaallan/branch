package com.androidstudy.branch

import android.app.Application
import androidx.annotation.Nullable
import com.androidstudy.branch.di.appModule
import com.androidstudy.branch.di.threadModule
import com.androidstudy.branch.settings.Settings
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import timber.log.Timber

class Branch : Application() {

    lateinit var settings: Settings

    override fun onCreate() {
        super.onCreate()

        settings = Settings(applicationContext)

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        try {
            startKoin {
                androidLogger()
                androidContext(applicationContext)
                listOf(appModule, threadModule)
            }
        } catch (error: KoinAppAlreadyStartedException) {
            Timber.e(error.localizedMessage)
        }
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