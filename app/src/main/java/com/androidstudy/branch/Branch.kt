package com.androidstudy.branch

import android.app.Application
import android.os.Build
import androidx.annotation.Nullable
import com.androidstudy.branch.di.*
import com.androidstudy.branch.settings.Settings
import com.androidstudy.branch.util.CrashlyticsTree
import com.facebook.stetho.Stetho
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class Branch : Application() {

    private var mInstance: Branch? = null
    lateinit var settings: Settings

    override fun onCreate() {
        super.onCreate()

        settings = Settings(applicationContext)
        mInstance = this

        initKoin()
        initTimber()
        initStetho()
    }

    private fun initStetho() {
        if (!isRoboUnitTest() && BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                retrofitModule,
                databaseModule,
                chatDaoModule,
                threadDaoModule,
                stockMessageDaoModule,
                chatRepoModule,
                threadRepoModule,
                stockMessageRepoModule,
                threadViewModel,
                chatViewModel,
                stockMessageViewModel
            )
        )
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                @Nullable
                override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }

    private fun isRoboUnitTest(): Boolean {
        return "robolectric" == Build.FINGERPRINT
    }

}