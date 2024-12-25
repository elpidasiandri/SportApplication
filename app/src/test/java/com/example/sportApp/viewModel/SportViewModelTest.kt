package com.example.sportApp.viewModel

import androidx.lifecycle.viewModelScope
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.models.domain.SportDomain
import com.example.sportApp.models.network.SportDto
import com.example.sportApp.models.network.SportEventDto
import com.example.sportApp.mock.MockDatabaseRepo
import com.example.sportApp.mock.MockNetworkRepo
import com.example.sportApp.mock.PreferenceManagerMock
import com.example.sportApp.useCases.db.DeleteDataFromDbUseCase
import com.example.sportApp.useCases.db.GetLocallySportsAndEventsUseCase
import com.example.sportApp.useCases.db.InsertSportsWithEventsUseCase
import com.example.sportApp.useCases.db.UpdateFavouriteSportUseCase
import com.example.sportApp.useCases.network.GetNetworkSportsUseCase
import com.example.sportApp.utils.MainDispatcherRule
import com.example.sportApp.viewModel.stateAndEvents.SportEvents
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SportViewModelTest {
    private lateinit var networkRepo: MockNetworkRepo
    private lateinit var databaseRepo: MockDatabaseRepo
    private lateinit var preferencesManager: PreferenceManagerMock
    private lateinit var ioDispatcher: CoroutineDispatcher
    private lateinit var viewModel: SportViewModel

    val mockList = listOf(
        SportDomain(
            sportId = "1",
            sportName = "Tennis",
            sportEvent = listOf(
                EventDomain(
                    eventId = "1",
                    sportId = "1",
                    eventName = "Eugenia-Chaney",
                    eventNameSecond = "Eugenia-Chaney",
                    startTime = 7420,
                    isFavourite = false

                ),
                EventDomain(
                    eventId = "2",
                    sportId = "1",
                    eventName = "ENA-DUO",
                    eventNameSecond = "ENA-DUO",
                    startTime = 7420,
                    isFavourite = false
                )
            )
        ),
        SportDomain(
            sportId = "3",
            sportName = "Volley",
            sportEvent = listOf(
                EventDomain(
                    eventId = "1",
                    sportId = "2",
                    eventName = "Aris-Olumpiakos",
                    eventNameSecond = "Aris-Olumpiakos",
                    startTime = 7420,
                    isFavourite = false

                )
            )
        )
    )
    val mockListDto = listOf(
        SportDto(
            sportId = "1",
            sportName = "Tennis",
            sportEvent = listOf(
                SportEventDto(
                    eventId = "1",
                    sportId = "1",
                    eventName = "Eugenia-Chaney",
                    eventNameSecond = "Eugenia-Chaney",
                    time = 7420,
                ),
                SportEventDto(
                    eventId = "2",
                    sportId = "1",
                    eventName = "ENA-DUO",
                    eventNameSecond = "ENA-DUO",
                    time = 7420,
                )
            )
        ),
        SportDto(
            sportId = "3",
            sportName = "Volley",
            sportEvent = listOf(
                SportEventDto(
                    eventId = "1",
                    sportId = "2",
                    eventName = "Aris-Olumpiakos",
                    eventNameSecond = "Aris-Olumpiakos",
                    time = 7420,
                )
            )
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        databaseRepo = MockDatabaseRepo()
        networkRepo = MockNetworkRepo(mockListDto)
        ioDispatcher = Dispatchers.IO
        preferencesManager = PreferenceManagerMock()
        viewModel = SportViewModel(
            ioDispatcher = ioDispatcher,
            getLocallySports = GetLocallySportsAndEventsUseCase(databaseRepo),
            insertSportsWithEvents = InsertSportsWithEventsUseCase(databaseRepo),
            getNetworkSports = GetNetworkSportsUseCase(networkRepo),
            preferencesManager = preferencesManager,
            updateFavouriteSport = UpdateFavouriteSportUseCase(databaseRepo),
            deleteDataFromDb = DeleteDataFromDbUseCase(databaseRepo)
        )
    }

    @Test
    fun testInitialState() {
        assertEquals(viewModel.state.value.isRefreshing, true)
        assertEquals(viewModel.state.value.didRefresh, false)
        assertEquals(viewModel.state.value.events, SportEvents.None)
        assertEquals(viewModel.state.value.showToast, false)
        assertEquals(viewModel.state.value.data, listOf<SportDomain>())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testHandleRefreshingEvent() = runTest {

        viewModel.handleEvent(SportEvents.Refreshing)
        advanceUntilIdle()
        assertEquals(viewModel.state.value.isRefreshing, true)
        assertEquals(viewModel.state.value.didRefresh, true)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFavouriteSportEvent() = runTest {

        viewModel.handleEvent(
            SportEvents.IsMyFavourite(
                eventId = "1",
                flag = true,
            )
        )
        advanceUntilIdle()
        assertEquals(
            databaseRepo.getLocallySportsAndEvents().first(), listOf<SportDomain>()
        )
    }

    @Test
    fun testSetTimer(){
        val newTimerValue:Long = 5000
        viewModel.setTimer(newTimerValue)
        assertEquals(newTimerValue,viewModel.state.value.currentTimer)
    }
}
