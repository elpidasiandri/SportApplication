package com.example.sportapp.models.domain

class SportDomain(
    val sportId: String,
    val sportName: String,
    val eventName: String,
    val eventNameSecond: String,
    val startTime: Int,
    val isFavourite: Boolean
)