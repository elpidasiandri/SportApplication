package com.example.sportApp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.models.domain.SportDomain
import kotlinx.coroutines.flow.*

fun List<SportsWithEvents>.toSportDomain(): List<SportDomain> {
    return this.map { sportItem ->
        SportDomain(
            sportId = sportItem.sport.sportId,
            sportName = sportItem.sport.sportName,
            sportEvent = sportItem.events.toDomainEvents(),
        )
    }
}

fun List<SportEventEntity>.toDomainEvents(): List<EventDomain> {
    return this.map { events ->
        EventDomain(
            eventId = events.eventId,
            sportId = events.sportId,
            eventName = events.eventName,
            eventNameSecond = events.eventNameSecond,
            startTime = events.startTime,
            isFavourite = events.isFavourite,
        )
    }
}

fun <T> Flow<T>.catchAndHandleError(
    onError: (String, Int) -> Unit,
): Flow<T> {
    return this
        .catch { error ->
            when (error) {
                is Exception -> {
                    val errorMessage = error.localizedMessage ?: "An error occurred"
                    val errorCode = 500
                    onError(errorMessage, errorCode)
                }

                else -> {
                    val errorMessage = "Unknown error"
                    val errorCode = 1000
                    onError(errorMessage, errorCode)
                }
            }
        }
}

fun Long.formatTime(): String {
    if (this < 0) {
        val endTime = -this
        val hours = endTime / 3600000
        val minutes = (endTime % 3600000) / 60000
        val seconds = (endTime % 60000) / 1000
        return "Event has been over ${String.format("%02d:%02d:%02d", hours, minutes, seconds)}"
    }
    val hours = this / 3600000
    val minutes = (this % 3600000) / 60000
    val seconds = (this % 60000) / 1000
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
fun String.splitText(): Pair<String, String> {
    return try {
        val  (part1,part2)  = this.split("-")
        Pair(part1,part2)
    } catch (e: Exception) {
        Pair(this,this)
    }
}
fun Modifier.noRippleClickable(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource,
): Modifier {
    return this.clickable(
        onClick = onClick,
        indication = null,
        interactionSource = interactionSource
    )
}

fun String.scaleTextToFitWidth(
    density: Density,
    availableWidthPx: Float,
    fontSize: TextUnit = 14.sp,
    minFontSize: TextUnit = 8.sp,
): TextUnit {
    val maxFontSizePx = with(density) { fontSize.toPx() }
    val minFontSizePx = with(density) { minFontSize.toPx() }

    val textWidth = measureTextWidth(density, this, fontSize)

    if (textWidth <= availableWidthPx) {
        return fontSize
    }

    val newFontSizePx = (availableWidthPx / textWidth * maxFontSizePx).coerceAtLeast(minFontSizePx)

    return with(density) { newFontSizePx.toSp() }
}

private fun measureTextWidth(density: Density, text: String, fontSize: TextUnit): Float {
    val textPaint = android.graphics.Paint()
    textPaint.textSize = with(density) { fontSize.toPx() }
    return textPaint.measureText(text)
}