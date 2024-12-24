package com.example.sportApp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.example.sportApp.db.entities.SportEventEntity
import com.example.sportApp.db.entities.SportsWithEvents
import com.example.sportApp.models.domain.EventDomain
import com.example.sportApp.models.domain.SportDomain
import kotlinx.coroutines.flow.*

    fun List<SportsWithEvents>.toSportDomain(): List<SportDomain> {
        return this.map { sportItem->
            SportDomain(
                sportId = sportItem.sport.sportId,
                sportName =sportItem.sport.sportName,
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
        onError: (String, Int) -> Unit
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
    val totalSeconds = this / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
fun String.replaceDashWithSpace(): String {
    return try {
        this.replace("-", " ")
    } catch(e:Exception){
        this
    }
}
fun Modifier.noRippleClickable(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource
): Modifier {
    return this.clickable(
        onClick = onClick,
        indication = null, // Disables the ripple effect
        interactionSource = interactionSource // Use the passed interactionSource
    )
}