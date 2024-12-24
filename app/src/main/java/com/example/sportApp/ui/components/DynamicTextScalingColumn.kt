package com.example.sportApp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.sportytimes.ui.theme.red
import com.app.sportytimes.ui.theme.white
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.utils.splitText
import com.example.sportApp.utils.scaleTextToFitWidth

@Composable
fun DynamicTextScalingColumn(sportEvent: EventDomain) {
    val density = LocalDensity.current
    var availableWidthPx by remember { mutableStateOf(0f) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .onGloballyPositioned { coordinates ->
            availableWidthPx = coordinates.size.width.toFloat()
        }

    ) {
        val (eventNameFirst,eventNameSecond) = sportEvent.eventName.splitText()
        val eventNameFontSize = eventNameFirst.scaleTextToFitWidth(density, availableWidthPx)

        Text(
            text = eventNameFirst,
            style = MaterialTheme.typography.bodySmall.copy(color = white, fontSize = eventNameFontSize),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = "vs",
            style = MaterialTheme.typography.bodyMedium.copy(color = red),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        val eventNameSecondFontSize = eventNameSecond.scaleTextToFitWidth(density,availableWidthPx)

        Text(
            text = eventNameSecond,
            style = MaterialTheme.typography.bodySmall.copy(color = white, fontSize = eventNameSecondFontSize),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            color = Color.White,
            thickness = 1.dp
        )
    }
}