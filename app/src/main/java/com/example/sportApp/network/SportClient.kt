package com.example.sportApp.network

import com.example.sportApp.models.network.SportDto
import retrofit2.http.GET

interface SportClient {
    @GET("sports.json")
    suspend fun getSports(): List<SportDto?>?
}