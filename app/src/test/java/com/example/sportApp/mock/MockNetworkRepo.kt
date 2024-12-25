package com.example.sportApp.mock

import com.example.sportApp.models.network.SportDto
import com.example.sportApp.mock.network.ISportNetworkRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockNetworkRepo (private val mockList: List<SportDto?>?): ISportNetworkRepo {
    override suspend fun getSports(): Flow<List<SportDto?>?> {
        return flow { emit(mockList) }
    }
}