package com.example.sportapp.network

import com.example.sportapp.models.network.SportDto
import retrofit2.http.GET

interface SportClient {
    @GET("sports.json")
    suspend fun getSports(): List<SportDto>
}