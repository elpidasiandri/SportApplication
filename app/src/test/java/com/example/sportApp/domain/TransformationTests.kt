package com.example.sportApp.domain
import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import com.example.sportApp.utils.toDomainEvents
import com.example.sportApp.utils.toSportDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class TransformationTests {

    @Test
    fun testToSportDomain() {
        val sportsWithEvents = listOf(
            SportsWithEvents(
                sport = SportEntity(sportId = "1", sportName = "Football"),
                events = listOf(
                    SportEventEntity(
                        eventId = "100",
                        sportId = "1",
                        eventName = "TeamA-TeamB",
                        eventNameSecond = "TeamA-TeamB",
                        startTime = 1633024800,
                        isFavourite = true
                    )
                )
            )
        )

        val result = sportsWithEvents.toSportDomain()
        assertEquals(1, result.size)
        assertEquals("Football", result[0].sportName)
        assertEquals(1, result[0].sportEvent.size)
        assertEquals("TeamA-TeamB", result[0].sportEvent[0].eventName)
    }

    @Test
    fun testToDomainEvents() {
        val eventEntities = listOf(
            SportEventEntity(
                eventId = "100",
                sportId = "1",
                eventName = "TeamA-TeamB",
                eventNameSecond = "TeamA-TeamB",
                startTime = 1633024800,
                isFavourite = true
            )
        )

        val result = eventEntities.toDomainEvents()
        assertEquals(1, result.size)
        assertEquals("TeamA-TeamB", result[0].eventName)
        assertEquals("TeamA-TeamB", result[0].eventNameSecond)
    }
}