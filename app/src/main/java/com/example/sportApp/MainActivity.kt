package com.example.sportApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.app.sportytimes.ui.theme.SportAppTheme
import com.app.sportytimes.ui.theme.gray
import com.example.sportApp.ui.components.TimerScreen
import com.example.sportApp.ui.views.App
import com.example.sportApp.viewModel.SportViewModel
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.androidx.viewmodel.ext.android.viewModel
class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext() {
                SportAppTheme {
                    Surface(
                        color = gray
                    ) {
                        App()
                        TimerScreen()
                    }
                }
            }
        }
    }
}