package com.example.sportapp.di

import com.example.sportapp.repositories.network.ISportNetworkRepo
import com.example.sportapp.repositories.network.SportNetworkRepoImpl
import com.example.sportapp.useCases.network.GetNetworkSportsUseCase
import com.example.sportapp.manager.PreferencesManager
import com.example.sportapp.viewModel.SportViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sportModule = module {
    viewModel {
        SportViewModel(get(), get(), get(), get(), get())
    }
    single { PreferencesManager(get()) }
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<ISportNetworkRepo> { SportNetworkRepoImpl(get()) }
    single { GetNetworkSportsUseCase(get()) }
}