package com.example.sportApp.models.network

import com.example.sportApp.models.domain.SportDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportEventDto(
    @Json(name = "i") val eventId: String?,
    @Json(name = "si")val sportId: String?,
    @Json(name = "d") val eventName: String?,
    @Json(name = "sh")val eventNameSecond: String?,
    @Json(name = "tt")val time: Int?
)

