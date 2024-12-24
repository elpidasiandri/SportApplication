package com.example.sportApp.ui.components

import androidx.compose.runtime.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.sportApp.viewModel.SportViewModel
import kotlinx.coroutines.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimerScreen(
    vm: SportViewModel = koinViewModel()
) {
    var currentTimeMillis by remember { mutableStateOf(System.currentTimeMillis()) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    var timerJob: Job? by remember { mutableStateOf(null) }

    fun startTimer() {
        currentTimeMillis = System.currentTimeMillis()
        timerJob = coroutineScope.launch {
            while (true) {
                delay(1000)
                currentTimeMillis += 1000
                vm.setTimer(timer = currentTimeMillis)
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle

        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    startTimer()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    stopTimer()
                }
                else -> Unit
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
            stopTimer()
        }
    }

}