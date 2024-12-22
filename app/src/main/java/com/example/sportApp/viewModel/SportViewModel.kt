package com.example.sportapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportapp.useCases.db.GetLocallySportsAndEventsUseCase
import com.example.sportapp.useCases.db.InsertSportsWithEventsUseCase
import com.example.sportapp.useCases.network.GetNetworkSportsUseCase
import com.example.sportapp.manager.PreferencesManager
import com.example.sportapp.models.domain.SportDomain
import com.example.sportapp.models.network.toSportDomainList
import com.example.sportapp.utils.Extensions.toSportDomainList
import com.example.sportapp.viewModel.stateAndEvents.SportEvents
import com.example.sportapp.viewModel.stateAndEvents.SportUiState
import kotlinx.coroutines.CoroutineDispatcher
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
) : ViewModel() {

    private val _state = MutableStateFlow(SportUiState())
    val state: StateFlow<SportUiState>
        get() = _state

    init {

        listenToLocalItems()
    }

    private fun listenToLocalItems() {
        viewModelScope.launch {
            if (preferencesManager.checkIfShouldDoHttpCall()) {
                withContext(ioDispatcher) {
                    getNetworkSports().catch {

                    }
                        .collectLatest { response ->
                            val data = response.toSportDomainList()

                            updateData(data)
                        }
                }
            } else {
                withContext(ioDispatcher) {
                    flow {
                        emit(getLocallySports())
                    }.catch {

                    }
                        .collectLatest { locallyData ->
                            updateData(locallyData.toSportDomainList())
                        }
                }


            }
        }
    }

    private fun updateData(data: List<SportDomain>) {
        _state.update {
            it.copy(
                events = if (data.isEmpty()) SportEvents.IsEmpty else SportEvents.None,
                data = data
            )
        }
    }
}