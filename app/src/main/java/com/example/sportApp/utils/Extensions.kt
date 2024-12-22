package com.example.sportapp.utils

import com.example.sportapp.db.entities.SportEntity
import com.example.sportapp.db.entities.SportEventEntity
import com.example.sportapp.db.entities.SportsWithEvents
import com.example.sportapp.models.domain.SportDomain

object Extensions {
    private fun SportEntity.toSportDomain(events: List<SportEventEntity>): SportDomain {
        val firstEvent = events.firstOrNull()

        return SportDomain(
            sportId = this.sportId,
            sportName = this.sportName,
            eventName = firstEvent?.eventName ?: "",
            eventNameSecond = firstEvent?.eventNameSecond ?: "",
            startTime = firstEvent?.startTime?: 0,
            isFavourite = firstEvent?.isFavourite ?: false
        )
    }

    fun List<SportsWithEvents>.toSportDomainList(): List<SportDomain> {
        return this.flatMap { sportsWithEvents ->
            listOf(sportsWithEvents.sport.toSportDomain(sportsWithEvents.events))
        }
    }

}