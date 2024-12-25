package com.example.sportApp.useCases

import com.example.sportApp.models.network.SportDto
import com.example.sportApp.models.network.SportEventDto
import com.example.sportApp.mock.MockNetworkRepo
import com.example.sportApp.useCases.network.GetNetworkSportsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GetSportsAndEventsFromNetworkUseCaseTest {
    private lateinit var networkRepo: MockNetworkRepo
    val mockListSport = listOf(
        SportDto(
            sportId = "1", sportName = "Tennis", sportEvent = listOf(
                SportEventDto(
                    eventId = "1",
                    sportId = "1",
                    eventName = "PAOK-ARIS",
                    eventNameSecond = "PAOK-ARIS",
                    time = 1234567

                ),
                SportEventDto(
                    eventId = "2",
                    sportId = "1",
                    eventName = "PAOK-PANSERRAIKOS",
                    eventNameSecond = "PAOK-PANSERRAIKOS",
                    time = 1234567
                )
            )
        ),
        SportDto(
            sportId = "2", sportName = "Volley", sportEvent = null
        )
    )
    val nullMockList: List<SportDto>? = null
    val mockListWithNullElements = listOf<SportDto?>(
        null,
        SportDto(
            sportId = "2", sportName = "Volley", sportEvent = null
        )
    )
    @Before
    fun prepareMockRepositories() {
        networkRepo = MockNetworkRepo(mockListSport)
    }

    @Test
    fun `Check that GetNetworkSportsUseCase handles valid data correctly`() = runTest {
        val useCase = GetNetworkSportsUseCase(networkRepo)

        useCase().collect { sportsList ->
            assertNotNull(sportsList)

            assertEquals(2, sportsList.size ?: 0)

            val firstSport = sportsList[0]
            assertNotNull(firstSport)
            assertEquals("Tennis", firstSport.sportName)
            assertEquals(2, firstSport?.sportEvent?.size ?: 0)

            val secondSport = sportsList[1]
            assertNotNull(secondSport)
            assertEquals("Volley", secondSport.sportName)
            assertNull(secondSport.sportEvent)
        }
    }

    @Test
    fun `Check that GetNetworkSportsUseCase handles null data correctly (empty list)`() = runTest {
        networkRepo = MockNetworkRepo(nullMockList)

        val useCase = GetNetworkSportsUseCase(networkRepo)
        useCase().collect { sportsList ->
            assertNull(sportsList)
        }
    }

    @Test
    fun `Check that GetNetworkSportsUseCase handles list with null elements correctly`() = runTest {
        networkRepo = MockNetworkRepo(mockListWithNullElements)

        val useCase = GetNetworkSportsUseCase(networkRepo)
        useCase().collect { sportsList ->
            assertNotNull(sportsList)
            assertEquals(2, sportsList.size)

            assertNull(sportsList[0])

            val secondSport = sportsList[1]
            assertNotNull(secondSport)
            assertEquals("Volley", secondSport.sportName)
        }
    }
}