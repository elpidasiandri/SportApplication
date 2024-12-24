package com.example.sportApp.ui.components.sportRow

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sportApp.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import com.app.sportytimes.ui.theme.red
import com.app.sportytimes.ui.theme.white

@Composable
fun SportRow(
    sportName: String,
    showFavourites: Boolean,
    isOpenArrow: Boolean,
    setFavourite: (Boolean) -> Unit,
    arrowButtonAction: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(color = white)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            Canvas(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .height(24.dp)
                    .width(24.dp)
            ) {
                drawCircle(
                    color = red,
                    radius = size.minDimension / 2
                )
            }
            SportTitle(title = sportName)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleButton(showFavourites = showFavourites, toggleButtonAction = {
                setFavourite(it)
            })

            Button(
                modifier = Modifier
                    .width(80.dp)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = white),
                onClick = { arrowButtonAction(isOpenArrow) }) {
                Image(
                    painter = painterResource(id = if (isOpenArrow) R.drawable.arrow_up else R.drawable.arrow_down),
                    contentDescription = "opened/closed arrow",
                )
            }
        }
    }
}

@Preview
@Composable
fun SportRowPreview() {
    Column {
        SportRow("basket", true, true, {}, {})
        SportRow("basket", false, false, {}, {})
    }
}