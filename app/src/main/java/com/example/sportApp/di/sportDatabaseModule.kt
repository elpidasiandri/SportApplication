package com.example.sportapp.di

import androidx.room.Room
import com.example.sportapp.db.SportDatabase
import com.example.sportapp.repositories.database.ISportDatabaseRepo
import com.example.sportapp.repositories.database.SportDatabaseRepoImpl
import com.example.sportapp.useCases.db.GetLocallySportsAndEventsUseCase
import com.example.sportapp.useCases.db.InsertSportsWithEventsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sportDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SportDatabase::class.java, "database-name"
        ).build()
    }

    single<ISportDatabaseRepo> { SportDatabaseRepoImpl(get(),get()) }
    single { GetLocallySportsAndEventsUseCase(get()) }
    single { InsertSportsWithEventsUseCase(get()) }
}