package com.example.sportApp.mock

import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import com.example.sportApp.mock.database.ISportDatabaseRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

class MockDatabaseRepo: ISportDatabaseRepo {
    private var sportsAndEvents = mutableListOf<SportsWithEvents>()

    override suspend fun setSportsAndEvents(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    ) {
        val groupedEvents = events.groupBy { it.sportId }
        sportsAndEvents.clear()
        sportsAndEvents.addAll(
            sports.map { sport ->
                SportsWithEvents(
                    sport = sport,
                    events = groupedEvents[sport.sportId] ?: emptyList()
                )
            }
        )
    }

    override suspend fun getLocallySportsAndEvents(): Flow<List<SportsWithEvents>> {
        return flow {
            emit(sportsAndEvents.toList())
        }
    }

    override suspend fun deleteAll() {
        sportsAndEvents.clear()
    }

    override suspend fun isMyFavourite(isMyFavourite: Boolean, eventId: String) {
        sportsAndEvents = sportsAndEvents.map { sportsWithEvents ->
            sportsWithEvents.copy(
                events = sportsWithEvents.events.map { event ->
                    if (event.eventId == eventId) {
                        event.copy(isFavourite = isMyFavourite)
                    } else {
                        event
                    }
                }
            )
        }.toMutableList()
    }
}