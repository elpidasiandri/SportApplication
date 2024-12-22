package com.example.sportapp.repositories.network

import com.example.sportapp.models.network.SportDto
import kotlinx.coroutines.flow.Flow

interface ISportNetworkRepo {
    suspend fun getSports(): Flow<List<SportDto>>
}