package com.example.sportApp.useCases.network

import com.example.sportApp.repositories.network.ISportNetworkRepo

class GetNetworkSportsUseCase(private val repo: ISportNetworkRepo) {
    suspend operator fun invoke() = repo.getSports()
}