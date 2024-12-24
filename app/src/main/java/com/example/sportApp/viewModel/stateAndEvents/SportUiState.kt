package com.example.sportApp.viewModel.stateAndEvents

import com.example.sportApp.models.domain.SportDomain
import kotlinx.coroutines.Job

data class SportUiState(
    val events: SportEvents = SportEvents.None,
    val isRefreshing: Boolean = true,
    val isEmpty: Boolean = false,
    val didRefresh: Boolean = false,
    val showToast: Boolean = false,
    val messageError: Int? = null,
    val currentTimer: Long= 0L,
    val data:List<SportDomain> = listOf()
)

sealed class SportEvents{
    data object Refreshing : SportEvents()
    data object None : SportEvents()
    data object IsEmpty : SportEvents()
    data class IsMyFavourite(val eventId: String, val flag:Boolean) : SportEvents()
}