package com.example.sportApp.ui.components.emptyScreens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.sportytimes.ui.theme.white
import com.example.sportApp.R

@Composable
fun EmptyScreenOfEvents(isMyFavourites: Boolean) {
    if (isMyFavourites) {
        Text(
            text = stringResource(id = R.string.no_favourites),
            style = MaterialTheme.typography.bodyMedium,
            color = white,
            modifier = Modifier.padding(24.dp)
        )
    } else {
        Text(
            text = stringResource(id = R.string.no_events),
            style = MaterialTheme.typography.bodyMedium,
            color = white,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyScreenOfEvents() {
    EmptyScreenOfEvents(isMyFavourites = true)
}