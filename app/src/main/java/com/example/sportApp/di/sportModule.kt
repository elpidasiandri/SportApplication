package com.example.sportApp.di

import com.example.sportApp.manager.IPreferencesManager
import com.example.sportApp.mock.network.ISportNetworkRepo
import com.example.sportApp.mock.network.SportNetworkRepoImpl
import com.example.sportApp.useCases.network.GetNetworkSportsUseCase
import com.example.sportApp.manager.PreferencesManagerImpl
import com.example.sportApp.viewModel.SportViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sportModule = module {
    viewModel {
        SportViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<IPreferencesManager> { PreferencesManagerImpl(androidContext()) }
    single<ISportNetworkRepo> { SportNetworkRepoImpl(get()) }
    single { GetNetworkSportsUseCase(get()) }
}