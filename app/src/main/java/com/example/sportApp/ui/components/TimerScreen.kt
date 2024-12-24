package com.example.sportApp.ui.components

import androidx.compose.runtime.*
import com.example.sportApp.viewModel.SportViewModel
import kotlinx.coroutines.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimerScreen(
    vm: SportViewModel = koinViewModel()
) {
    var seconds by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val job = scope.launch {
            while (true) {
                delay(1000)
                seconds++
            }
        }

        onDispose {
            job.cancel()
        }
    }

}