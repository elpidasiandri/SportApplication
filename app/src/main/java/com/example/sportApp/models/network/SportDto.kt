package com.example.sportapp.models.network

import com.example.sportapp.models.domain.SportDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportDto(
    @Json(name = "i") val sportId: String?,
    @Json(name = "d") val sportName: String?,
    @Json(name = "e") val sportEvent: List<SportEventDto>?
){
    fun convertToSportDomain(): List<SportDomain> {
        return this.sportEvent?.map { eventDto ->
            SportDomain(
                sportId = this.sportId ?: "",
                sportName = this.sportName ?: "",
                eventName = eventDto.eventName ?: "",
                eventNameSecond = eventDto.eventNameSecond ?: "",
                startTime = eventDto.time?:0,
                isFavourite = false
            )
        } ?: emptyList()
    }
}
fun List<SportDto>.toSportDomainList(): List<SportDomain> {
    return this.flatMap { it.convertToSportDomain() }
}
