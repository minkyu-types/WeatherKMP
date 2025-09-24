package org.dosys.androidapp

import android.app.Application
import org.dosys.project.appModule
import org.dosys.project.getRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                appModule()
                        + getRepositoryModule()
            )
        }
    }


}