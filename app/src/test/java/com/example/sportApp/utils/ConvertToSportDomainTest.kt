package com.example.sportApp.utils

import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.models.domain.SportDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertToSportDomainTest {
    @Test
    fun `test toSportDomain should map SportsWithEvents to SportDomain and correctly`() {
        val mockEventList = listOf(
            SportEventEntity(
                eventId = "1",
                sportId = "1",
                eventName = "PAOK-PANSERRAIKOS",
                eventNameSecond = "PAOK-PANSERRAIKOS",
                startTime = 0,
                isFavourite = false
            )
        )
        val mockSportList = SportEntity(
            sportId = "1", sportName = "Tennis"

        )
        val sportWithEvents = SportsWithEvents(
            sport = mockSportList,
            events = mockEventList
        )

        val expectedSportDomain = SportDomain(
            sportId = "1",
            sportName = "Tennis",
            sportEvent = listOf(
                EventDomain(
                    eventId = "1",
                    sportId = "1",
                    eventName = "PAOK-PANSERRAIKOS",
                    eventNameSecond = "PAOK-PANSERRAIKOS",
                    startTime = 0,
                    isFavourite = false
                )
            )
        )

        val sportDomainList = listOf(sportWithEvents).toSportDomain()

        assertEquals(1, sportDomainList.size)
        assertEquals(expectedSportDomain.sportId, sportDomainList[0].sportId)
        assertEquals(expectedSportDomain.sportName, sportDomainList[0].sportName)
        assertEquals(expectedSportDomain.sportEvent, sportDomainList[0].sportEvent)
    }

    @Test
    fun `test toDomainEvents should map SportEventEntity to EventDomain correctly`() {
        val mockEventList = listOf(
            SportEventEntity(
                eventId = "1",
                sportId = "1",
                eventName = "PAOK-PANSERRAIKOS",
                eventNameSecond = "PAOK-PANSERRAIKOS",
                startTime = 0,
                isFavourite = false
            )
        )

        val expectedEventDomainList = listOf(
            EventDomain(
                eventId = "1",
                sportId = "1",
                eventName = "PAOK-PANSERRAIKOS",
                eventNameSecond = "PAOK-PANSERRAIKOS",
                startTime = 0,
                isFavourite = false
            )
        )

        val eventDomainList = mockEventList.toDomainEvents()

        assertEquals(1, eventDomainList.size)
        assertEquals(expectedEventDomainList[0].eventId, eventDomainList[0].eventId)
        assertEquals(expectedEventDomainList[0].sportId, eventDomainList[0].sportId)
        assertEquals(expectedEventDomainList[0].eventName, eventDomainList[0].eventName)
        assertEquals(expectedEventDomainList[0].eventNameSecond, eventDomainList[0].eventNameSecond)
        assertEquals(expectedEventDomainList[0].startTime, eventDomainList[0].startTime)
        assertEquals(expectedEventDomainList[0].isFavourite, eventDomainList[0].isFavourite)
    }
}