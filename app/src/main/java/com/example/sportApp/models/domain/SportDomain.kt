package com.example.sportApp.models.domain


data class SportDomain(
    val sportId: String,
    val sportName: String,
    val sportEvent: List<EventDomain>
)

data class EventDomain(
    val eventId: String,
    val sportId: String,
    val eventName: String,
    val eventNameSecond: String,
    val startTime: Int,
    val isFavourite: Boolean
)