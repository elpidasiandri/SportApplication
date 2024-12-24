package com.example.sportApp.models.network

import com.example.sportApp.models.domain.SportDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportDto(
    @Json(name = "i") val sportId: String?,
    @Json(name = "d") val sportName: String?,
    @Json(name = "e") val sportEvent: List<SportEventDto>?
)