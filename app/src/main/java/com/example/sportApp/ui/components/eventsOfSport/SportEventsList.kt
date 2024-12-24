package com.example.sportApp.ui.components.eventsOfSport

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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
    AnimatedVisibility(
        visible = isMyFavourites,
        enter = fadeIn(animationSpec = tween(durationMillis = 150)) + slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(durationMillis = 150)
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 100)) + slideOutVertically(
            targetOffsetY = { it / 2 },
            animationSpec = tween(durationMillis = 100)
        )
    ) {
        val favouriteItems = remember(eventsList) {
            eventsList.filter {
                it.isFavourite
            }
        }
        if (favouriteItems.isEmpty()) {
            EmptyScreenOfEvents(isMyFavourites)
        } else {
            ListOfEvents(
                pageSize = pageSize,
                eventsList = favouriteItems,
                getCurrentTime = getCurrentTime,
                onFavoriteAction = onFavoriteAction
            )
        }
    }
    AnimatedVisibility(
        visible = !isMyFavourites,
        enter = fadeIn(animationSpec = tween(durationMillis = 100)) + slideInVertically(
            initialOffsetY = { it / 2 },
            animationSpec = tween(durationMillis = 150)
        ),
        exit = fadeOut(animationSpec = tween(durationMillis = 150)) + slideOutVertically(
            targetOffsetY = { it / 2 },
            animationSpec = tween(durationMillis = 100)
        )
    ) {
        if (eventsList.isEmpty()) {
            EmptyScreenOfEvents(isMyFavourites)
        } else {
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
    val pages by remember(eventsList.size) {
        mutableIntStateOf(eventsList.size / pageSize)
    }
    var currentPage by remember(eventsList.size) {
        mutableIntStateOf(0)
    }

    val currentIndex by remember(currentPage, eventsList.size) {
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