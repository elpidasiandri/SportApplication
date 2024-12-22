package com.example.sportapp.useCases.network

import com.example.sportapp.repositories.network.ISportNetworkRepo

class GetNetworkSportsUseCase(private val repo: ISportNetworkRepo) {
    suspend operator fun invoke() = repo.getSports()
}