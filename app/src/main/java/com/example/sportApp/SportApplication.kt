package com.example.sportapp

import android.app.Application
import com.example.sportapp.di.networkModule
import com.example.sportapp.di.sportDatabaseModule
import com.example.sportapp.di.sportModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SportApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SportApplication)
            modules(networkModule, sportModule,sportDatabaseModule)
        }
    }
}