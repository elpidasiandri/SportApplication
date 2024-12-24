package com.example.sportApp.ui.components.eventsOfSport

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.ui.components.emptyScreens.EmptyScreenOfEvents

@Composable
fun SportEventsList(
    pageSize: Int = 10,
    isMyFavourites: Boolean,
    eventsList: List<EventDomain>,
    getCurrentTime: () -> Long,
    onFavoriteAction: (String, Boolean) -> Unit,
) {
    if (isMyFavourites) {
        val favouriteItems = remember(eventsList) {
            eventsList.filter {
                it.isFavourite
            }
        }
        if (favouriteItems.isEmpty()){
            EmptyScreenOfEvents(isMyFavourites)
        }else{
            ListOfEvents(
                pageSize = pageSize,
                eventsList = favouriteItems,
                getCurrentTime = getCurrentTime,
                onFavoriteAction = onFavoriteAction
            )
        }

    } else {
        if (eventsList.isEmpty()){
            EmptyScreenOfEvents(isMyFavourites)
        }else{
            ListOfEvents(
                pageSize = pageSize,
                eventsList = eventsList,
                getCurrentTime = getCurrentTime,
                onFavoriteAction = onFavoriteAction
            )
        }

    }
}

@Composable
fun ListOfEvents(
    pageSize: Int,
    eventsList: List<EventDomain>,
    getCurrentTime: () -> Long,
    onFavoriteAction: (String, Boolean) -> Unit,
) {
    val pages by remember(eventsList) {
        mutableIntStateOf(eventsList.size / pageSize)
    }
    var currentPage by remember(eventsList) {
        mutableIntStateOf(0)
    }

    val currentIndex by remember(currentPage, eventsList) {
        mutableIntStateOf(currentPage * pageSize)
    }

    Column {
        (currentIndex..<currentIndex + pageSize).forEach { index ->
            if (index < eventsList.size) {
                val currentItem by remember(currentPage) {
                    mutableStateOf(eventsList[index])
                }
                SportEventItem(
                    sportEvent = currentItem,
                    currentTime = getCurrentTime(),
                    onFavoriteAction = onFavoriteAction
                )
            }
        }
        if (eventsList.size > pageSize) {
            EventsPaginator(currentPage, pages) {
                when (it) {
                    EventsPaginatorEnum.Increased -> currentPage++
                    EventsPaginatorEnum.Decreased -> currentPage--
                }
            }
        }
    }
}