package com.example.sportapp.ui.views

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.sportapp.viewModel.SportViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(viewModel: SportViewModel = koinViewModel()) {

}
