package com.androidstudy.branch

import android.app.Application
import androidx.annotation.Nullable
import com.androidstudy.branch.di.*
import com.androidstudy.branch.settings.Settings
import com.facebook.stetho.Stetho
import org.jetbrains.annotations.NotNull
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class Branch : Application() {

    lateinit var settings: Settings

    override fun onCreate() {
        super.onCreate()

        settings = Settings(applicationContext)

        initKoin()
        initTimber()
        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(retrofitModule, databaseModule, daoModule, repositoryModule, viewModelModule)
        )
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