package com.test.downloadmanager

import android.app.Application
import com.test.downloadmanager.di.SharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(SharedModules)
            androidContext(applicationContext)
        }

    }
}