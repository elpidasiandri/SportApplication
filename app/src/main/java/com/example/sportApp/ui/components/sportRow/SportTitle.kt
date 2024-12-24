package com.example.sportApp.ui.components.sportRow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.sportytimes.ui.theme.SportAppTheme
import com.app.sportytimes.ui.theme.black

@Composable
fun SportTitle(title: String) {
    Text(
        text = title,
        color = black,
        fontSize = 18.sp,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(8.dp),
    )
}

@Preview
@Composable
fun PreviewTitle() {
    SportAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = black
        ) {
            SportTitle(title = "Football")
        }
    }
}