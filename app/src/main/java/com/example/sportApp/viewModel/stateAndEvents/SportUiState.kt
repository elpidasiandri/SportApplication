package com.example.sportapp.viewModel.stateAndEvents

import com.example.sportapp.models.domain.SportDomain

data class SportUiState(
    val events: SportEvents = SportEvents.None,
    val isRefreshing: Boolean = true,
    val isEmpty: Boolean = false,
    val data:List<SportDomain> = listOf()
)

sealed class SportEvents{
    data object Refreshing : SportEvents()
    data object None : SportEvents()
    data object IsEmpty : SportEvents()
    data class Error(val message: Int) : SportEvents()
}