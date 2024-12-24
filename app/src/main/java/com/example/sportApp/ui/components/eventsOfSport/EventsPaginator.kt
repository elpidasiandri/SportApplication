package com.example.sportApp.ui.components.eventsOfSport

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.sportytimes.ui.theme.Typography
import com.app.sportytimes.ui.theme.white
import com.example.sportApp.utils.noRippleClickable

@Composable
fun EventsPaginator(currentPage: Int, pages: Int, action: (EventsPaginatorEnum) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Text(text = "Previous", color = white, style = Typography.bodySmall, modifier = if (currentPage > 0) Modifier.noRippleClickable(
            interactionSource = interactionSource,
            onClick = {

                action(EventsPaginatorEnum.Decreased)
            }
        ) else Modifier
        )
        Spacer(Modifier.padding(2.dp))
        Text(text = "Page ${currentPage + 1} of ${pages + 1}", color = white, style = Typography.bodySmall)
        Spacer(Modifier.padding(2.dp))
        Text(text = "Next", color = white, style = Typography.bodySmall, modifier = if (currentPage <= pages) Modifier.noRippleClickable(
            interactionSource = interactionSource,
            onClick =  {
                action(EventsPaginatorEnum.Increased)
            }
            ) else Modifier
        )
    }
}

enum class EventsPaginatorEnum() {
    Increased,
    Decreased
}