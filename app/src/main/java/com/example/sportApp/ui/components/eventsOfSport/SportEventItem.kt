package com.example.sportApp.ui.components.eventsOfSport

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.sportytimes.ui.theme.lightBlue
import com.app.sportytimes.ui.theme.white
import com.example.sportApp.R
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.utils.formatTime
import com.example.sportApp.utils.replaceDashWithSpace

@Composable
fun SportEventItem(
    sportEvent: EventDomain,
    currentTime: Long,
    onFavoriteAction: (String, Boolean) -> Unit,

    ) {
    val remainingTime = sportEvent.startTime * 1000L - currentTime
    val matchTimeFormatted = remainingTime.formatTime()
    var isFavourite by remember(sportEvent.eventId) { mutableStateOf(sportEvent.isFavourite) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = matchTimeFormatted,
            style = MaterialTheme.typography.bodySmall.copy(color = white),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .border(2.dp, lightBlue, RoundedCornerShape(8.dp))
                .padding(8.dp)
        )

        IconButton(
            onClick = {
                isFavourite = !isFavourite
                onFavoriteAction(sportEvent.eventId, isFavourite)
            }
        ) {
            Icon(
                painter = painterResource(id = if (isFavourite) R.drawable.star_my_favourite else R.drawable.star_not_my_favourite),
                contentDescription = if (isFavourite) "Delete from my favourites" else "Add to my favourites",
                tint = if (isFavourite) Color.Yellow else Color.Gray
            )
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = sportEvent.eventName.replaceDashWithSpace(),
            style = MaterialTheme.typography.bodySmall.copy(color = white)
        )
        Text(
            text = "vs",
            style = MaterialTheme.typography.bodyMedium.copy(color = white),
            color = Color.Red
        )
        Text(
            text = sportEvent.eventNameSecond.replaceDashWithSpace(),
            style = MaterialTheme.typography.bodySmall.copy(color = white)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = white,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSportEventsList() {
    val sampleEvents = EventDomain(
        sportId = "1",
        eventName = "Team A",
        eventNameSecond = "Team B",
        startTime = (System.currentTimeMillis() / 1000L).toInt() + 3600,
        isFavourite = true,
        eventId = "3"
    )


    SportEventItem(
        sportEvent = sampleEvents,
        currentTime = System.currentTimeMillis() / 1000L,
        onFavoriteAction = { eventId, flag ->
        }
    )
}