package com.example.sportApp.repositories.network

import com.example.sportApp.models.network.SportDto
import kotlinx.coroutines.flow.Flow

interface ISportNetworkRepo {
    suspend fun getSports(): Flow<List<SportDto?>?>
}