package com.example.sportApp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sportApp.ui.components.SportUiAndEventsContainer
import com.example.sportApp.ui.components.emptyScreens.EmptyScreenOfSports
import com.example.sportApp.ui.components.errorToast.CustomToast
import com.example.sportApp.viewModel.SportViewModel
import com.example.sportApp.viewModel.stateAndEvents.SportEvents
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App(
    vm: SportViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()

    val refresherState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = {
            vm.handleEvent(SportEvents.Refreshing)
        }
    )
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(refresherState)
    ) {
        if (state.data.isEmpty() && !state.isRefreshing){
            EmptyScreenOfSports(refresh = {vm.handleEvent(SportEvents.Refreshing)})
        }else{
            LazyColumn {
                items(count = state.data.size) { index ->
                    Spacer(
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    SportUiAndEventsContainer(
                        state.data[index],
                        getCurrentTime = {
                            state.currentTimer
                        },
                        onFavoriteAction= { id, flag->
                            vm.handleEvent(
                                SportEvents.IsMyFavourite(
                                    flag = flag,
                                    eventId = id
                                )
                            )

                        }
                    )
                }
            }
        }

        if (state.showToast) {
            state.messageError?.let { stringResource(id = it) }?.let {
                CustomToast(
                    message = it,
                    position = Alignment.BottomCenter
                )
            }
        }
        PullRefreshIndicator(
            state.isRefreshing,
            refresherState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

