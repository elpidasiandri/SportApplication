package com.example.sportApp.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.models.domain.SportDomain
import com.example.sportApp.ui.components.emptyScreens.EmptyScreenOfEvents
import com.example.sportApp.ui.components.eventsOfSport.SportEventsList
import com.example.sportApp.ui.components.sportRow.SportRow

@Composable
fun SportUiAndEventsContainer(
    sport: SportDomain,
    getCurrentTime: () -> Long,
    onFavoriteAction: (String, Boolean) -> Unit,
) {
    var isOpenArrow by rememberSaveable { mutableStateOf(false) }
    var showFavourites by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column() {
            SportRow(
                sportName = sport.sportName,
                showFavourites = showFavourites,
                isOpenArrow = isOpenArrow,
                setFavourite = {
                    showFavourites = it },
                arrowButtonAction = { isOpenArrow = !it })
            if (isOpenArrow) {
                SportEventsList(
                    eventsList = sport.sportEvent,
                    isMyFavourites = showFavourites,
                    getCurrentTime = getCurrentTime,
                    onFavoriteAction = onFavoriteAction
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSportUiAndEventsContainer() {
    val mockSport = SportDomain(
        sportId = "1",
        sportName = "Football",
        sportEvent = listOf(
            EventDomain(
                eventName = "Match 1",
                startTime = 1698874,
                isFavourite = false,
                eventNameSecond = "paok",
                sportId = "1",
                eventId = "1"
            ),
            EventDomain(
                eventName = "Match 2",
                startTime = 16988856,
                isFavourite = true,
                eventNameSecond = "paok",
                sportId = "1",
                eventId = "2"
            )
        )
    )

    SportUiAndEventsContainer(
        sport = mockSport,
        getCurrentTime = { System.currentTimeMillis() },
        onFavoriteAction = { eventId, isFavorite ->
        }
    )
}

