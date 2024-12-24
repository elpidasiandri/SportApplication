package com.example.sportApp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportApp.useCases.db.UpdateFavouriteSportUseCase
import com.example.sportApp.R
import com.example.sportApp.db.entities.SportEntity
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.manager.PreferencesManager
import com.example.sportApp.models.domain.SportDomain
import com.example.sportApp.useCases.db.DeleteDataFromDbUseCase
import com.example.sportApp.useCases.db.GetLocallySportsAndEventsUseCase
import com.example.sportApp.useCases.db.InsertSportsWithEventsUseCase
import com.example.sportApp.useCases.network.GetNetworkSportsUseCase
import com.example.sportApp.utils.catchAndHandleError
import com.example.sportApp.utils.toSportDomain
import com.example.sportApp.viewModel.stateAndEvents.EventJobForUpdateFavourite
import com.example.sportApp.viewModel.stateAndEvents.SportEvents
import com.example.sportApp.viewModel.stateAndEvents.SportUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SportViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val getLocallySports: GetLocallySportsAndEventsUseCase,
    private val insertSportsWithEvents: InsertSportsWithEventsUseCase,
    private val getNetworkSports: GetNetworkSportsUseCase,
    private val preferencesManager: PreferencesManager,
    private val updateFavouriteSport: UpdateFavouriteSportUseCase,
    private val deleteDataFromDb: DeleteDataFromDbUseCase,
) : ViewModel() {
    private var updateMyFavouriteSportJob: EventJobForUpdateFavourite? = null
    private val _state = MutableStateFlow(SportUiState())
    val state: StateFlow<SportUiState>
        get() = _state

    init {
        if (preferencesManager.checkIfShouldDoHttpCall()) {
            getSportFromNetwork()
        }
        listenToLocalItems()
    }

    private fun getSportFromNetwork() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                getNetworkSports().catchAndHandleError { errorMessage, errorCode ->
                    when (errorCode) {
                        500 -> {
                            showError(R.string.no_internet_connection)
                        }

                        else -> {
                            showError(R.string.something_went_wrong)
                        }
                    }
                }
                    .collectLatest { response ->
                        val sports = response.map { sportDomain ->
                            SportEntity(
                                sportId = sportDomain.sportId ?: "",
                                sportName = sportDomain.sportName ?: ""
                            )
                        }

                        val events: MutableList<SportEventEntity> = mutableListOf()
                        response.map { sport ->
                            sport.sportEvent?.map { event ->
                                events.add(
                                    SportEventEntity(
                                        eventId = event.eventId ?: "",
                                        sportId = event.sportId ?: "",
                                        eventName = event.eventName ?: "",
                                        eventNameSecond = event.eventNameSecond ?: "",
                                        startTime = event.time ?: 0,
                                        isFavourite = false
                                    )
                                )
                            }

                        }
                        deleteDataFromDbAndInsertNewData(sports, events)
                    }
            }
        }
    }

    private suspend fun deleteDataFromDbAndInsertNewData(
        sports: List<SportEntity>,
        events: List<SportEventEntity>,
    ) {
        flow { emit(deleteDataFromDb()) }.catch {
            showError(R.string.something_went_wrong)
        }.collectLatest {
            flow {
                emit(
                    insertSportsWithEvents(
                        sports = sports,
                        events = events
                    )
                )
            }.catch {
                showError(R.string.something_went_wrong)
            }.collectLatest {
                _state.update {
                    it.copy(
                        didRefresh = false
                    )
                }
                preferencesManager.setLastUpdatedTimestamp(System.currentTimeMillis())
            }
        }
    }

    private fun refresh() {
        _state.update {
            it.copy(
                isRefreshing = true,
                didRefresh = true,
                events = SportEvents.None
            )
        }
        viewModelScope.launch {
            getSportFromNetwork()
        }
    }

    private fun listenToLocalItems() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                getLocallySports().catch {}
                    .collectLatest { locallyData ->
                        updateData(locallyData.toSportDomain())
                    }
            }
        }

    }
    fun handleEvent(event: SportEvents) {
        viewModelScope.launch {
            when (event) {
                is SportEvents.Refreshing -> {
                    refresh()
                }

                is SportEvents.IsMyFavourite -> {
                    viewModelScope.launch(ioDispatcher) {
                        flow {
                            delay(400)
                            emit(updateFavouriteSport(event.flag, event.eventId))
                        }.catch { exception ->
                            showError(R.string.something_went_wrong)
                        }.collectLatest { result ->
                        }
                    }

//                    if (updateMyFavouriteSportJob?.eventId != event.eventId) {
//                        updateMyFavouriteSportJob?.job?.cancel()
//
//                        val newJob = viewModelScope.launch(ioDispatcher) {
//                            flow {
//                                delay(400)
//                                emit(updateFavouriteSport(event.flag, event.eventId))
//                            }.catch { exception ->
//                                showError(R.string.something_went_wrong)
//                            }.collectLatest { result ->
//                            }
//                        }
//
//                        updateMyFavouriteSportJob = EventJobForUpdateFavourite(event.eventId, newJob)
//                    }
                }

                else -> Unit
            }
        }
    }
     fun setTimer(timer:Long){
         _state.update {
             it.copy(
                currentTimer = timer
             )
         }
     }

    private fun showError(messageInt: Int) {
        _state.update {
            it.copy(
                showToast = true,
                messageError = messageInt,
                isRefreshing = false,
                data = emptyList(),
            )
        }
    }

    private fun updateData(data: List<SportDomain>) {
        if (!state.value.didRefresh){
            _state.update {
                it.copy(
                    events = if (data.isEmpty()) SportEvents.IsEmpty else SportEvents.None,
                    data = data,
                    isRefreshing = false,
                    messageError = null,
                    showToast = false,
                )
            }
        }
    }
}