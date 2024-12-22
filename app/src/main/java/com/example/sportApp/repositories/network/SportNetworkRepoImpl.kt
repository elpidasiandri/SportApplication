package com.example.sportapp.repositories.network

import com.example.sportapp.network.SportClient
import com.example.sportapp.models.network.SportDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SportNetworkRepoImpl(private val sportClient: SportClient) : ISportNetworkRepo {
    override suspend fun getSports(): Flow<List<SportDto>> {
        return flow {
            emit(sportClient.getSports())
        }
    }
}