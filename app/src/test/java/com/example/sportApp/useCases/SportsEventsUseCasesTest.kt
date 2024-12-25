package com.example.sportApp.useCases

import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.mock.MockDatabaseRepo
import com.example.sportApp.useCases.db.DeleteDataFromDbUseCase
import com.example.sportApp.useCases.db.GetLocallySportsAndEventsUseCase
import com.example.sportApp.useCases.db.InsertSportsWithEventsUseCase
import com.example.sportApp.useCases.db.UpdateFavouriteSportUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SportsEventsDatabaseUseCasesTest {
    private lateinit var localRepository: MockDatabaseRepo
    val mockListSport = listOf(
        SportEntity("1", "Football")
    )
    val mockListEvent = listOf(
        SportEventEntity(
            eventId = "1",
            sportId = "1",
            eventName = "Roxanne-Ball",
            eventNameSecond = "Roxanne-Ball",
            startTime = 3699,
            isFavourite = false
        ),
        SportEventEntity(
            eventId = "2",
            sportId = "1",
            eventName = "Roxanne-Rossie",
            eventNameSecond = "Roxanne-Rossie",
            startTime = 3699,
            isFavourite = false
        )
    )

    @Before
    fun prepareMockRepositories() {
        localRepository = MockDatabaseRepo()
    }
    @Test
    fun `Check that the InsertSportsWithEventsUseCase is saving the data correctly`() = runTest {
        val insertUseCase = InsertSportsWithEventsUseCase(localRepository)
        insertUseCase(mockListSport, mockListEvent)

        val sportsWithEvents = localRepository.getLocallySportsAndEvents().first()
        assertEquals(1, sportsWithEvents.size)
        assertEquals(2, sportsWithEvents.first().events.size)
        assertEquals("Football", sportsWithEvents.first().sport.sportName)
    }

    @Test
    fun `Check that the GetLocallySportsAndEventsUseCase is fetching the correct data`() = runTest {
        val insertUseCase = InsertSportsWithEventsUseCase(localRepository)
        insertUseCase(mockListSport, mockListEvent)
        val getUseCase = GetLocallySportsAndEventsUseCase(localRepository)
        val sportsWithEvents = getUseCase().first()
        assertEquals(1, sportsWithEvents.size)
        assertEquals("1", sportsWithEvents.first().sport.sportId)
        assertEquals("Football", sportsWithEvents.first().sport.sportName)
        assertEquals(2, sportsWithEvents.first().events.size)
    }

    @Test
    fun `Check that the UpdateFavouriteSportUseCase is correctly toggling the favourite status`() = runTest {
        val insertUseCase = InsertSportsWithEventsUseCase(localRepository)
        insertUseCase(mockListSport, mockListEvent)
        val updateFavoriteUseCase = UpdateFavouriteSportUseCase(localRepository)
        updateFavoriteUseCase(true, "1")

        val sportsWithEvents = localRepository.getLocallySportsAndEvents().first()
        val updatedEvent = sportsWithEvents.first().events.first { it.eventId == "1" }

        assertTrue(updatedEvent.isFavourite)
    }

    @Test
    fun `Check that the DeleteDataFromDbUseCase is clearing the data correctly`() = runTest {
        val insertUseCase = InsertSportsWithEventsUseCase(localRepository)
        insertUseCase(mockListSport, mockListEvent)
        val deleteUseCase = DeleteDataFromDbUseCase(localRepository)
        deleteUseCase()
        val sportsWithEvents = localRepository.getLocallySportsAndEvents().first()
        assertTrue(sportsWithEvents.isEmpty())
    }
}