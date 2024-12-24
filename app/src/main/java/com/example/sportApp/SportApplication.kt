package com.example.sportApp

import android.app.Application
import com.example.sportApp.di.networkModule
import com.example.sportApp.di.sportDatabaseModule
import com.example.sportApp.di.sportModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SportApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SportApplication)
            modules(networkModule, sportDatabaseModule, sportModule,)
        }
    }
}