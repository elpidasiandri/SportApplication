package com.example.sportapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sportapp.ui.theme.SportAppTheme
import com.example.sportapp.ui.theme.grey
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext() {
                SportAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = grey
                    ) {

                    }
                }
            }

        }
    }
}
