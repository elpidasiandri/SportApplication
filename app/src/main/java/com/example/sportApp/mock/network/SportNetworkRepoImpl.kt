package com.example.sportApp.mock.network

import com.example.sportApp.network.SportClient
import com.example.sportApp.models.network.SportDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SportNetworkRepoImpl(private val sportClient: SportClient) : ISportNetworkRepo {
    override suspend fun getSports(): Flow<List<SportDto?>?> {
        return flow {
            emit(sportClient.getSports())
        }
    }
}