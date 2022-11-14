package com.lucas.yourmarket.presentation.ui

import android.app.Application
import com.lucas.yourmarket.di.networkModule
import com.lucas.yourmarket.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : KoinComponent, Application() {

    override fun onCreate() {
        super.onCreate()

        // Start up koin and supply dependency modules
        startKoin {
            // Allows coin to inject Android context anywhere
            androidContext(this@App)

            modules(listOf(networkModule, storageModule))
        }
    }
}