package com.example.sportApp.di

import androidx.room.Room
import com.example.sportApp.useCases.db.UpdateFavouriteSportUseCase
import com.example.sportApp.db.SportDatabase
import com.example.sportApp.mock.database.ISportDatabaseRepo
import com.example.sportApp.mock.database.SportDatabaseRepoImpl
import com.example.sportApp.useCases.db.DeleteDataFromDbUseCase
import com.example.sportApp.useCases.db.GetLocallySportsAndEventsUseCase
import com.example.sportApp.useCases.db.InsertSportsWithEventsUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sportDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SportDatabase::class.java, "database-name"
        ).build()
    }
    single { get<SportDatabase>().sportsDao() }
    single { get<SportDatabase>().sportsWithEventsDao() }
    single<ISportDatabaseRepo> { SportDatabaseRepoImpl(get(),get()) }
    single { GetLocallySportsAndEventsUseCase(get()) }
    single { DeleteDataFromDbUseCase(get()) }
    single { UpdateFavouriteSportUseCase(get()) }
    single { InsertSportsWithEventsUseCase(get()) }
}