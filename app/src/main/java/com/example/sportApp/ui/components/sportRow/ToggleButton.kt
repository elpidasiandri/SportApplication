package com.example.sportApp.ui.components.sportRow

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.sportytimes.ui.theme.gray
import com.app.sportytimes.ui.theme.lightBlue
import com.app.sportytimes.ui.theme.white
import com.example.sportApp.utils.noRippleClickable

@Composable
fun ToggleButton(showFavourites: Boolean, toggleButtonAction: (Boolean) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .width(60.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (showFavourites) gray else Color.LightGray)
            .noRippleClickable(
                interactionSource = interactionSource ,
                onClick = {
                    toggleButtonAction(!showFavourites)
                }
            ) ,
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset(x = if (showFavourites) 28.dp else 4.dp)
                .clip(CircleShape)
                .background(if (showFavourites) lightBlue else gray)
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(if (showFavourites) white else gray, CircleShape)
                    .align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (showFavourites) lightBlue else Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}



@Preview
@Composable
fun switchPreview() {
    Column {
        ToggleButton(showFavourites = true, toggleButtonAction = {})
        ToggleButton(showFavourites = false, toggleButtonAction = {})
    }
}