package com.mmfsin.oneworld.presentation.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.events.components.EventCard
import com.mmfsin.oneworld.utils.NAV_EDIT_PROFILE
import com.mmfsin.oneworld.utils.openBedRockActivity

@Preview
@Composable
fun EventsScreenPV() {
    Column() {
        EventsContent(
            uiState = EventsStates(
                events = emptyList()
            )
        )
    }
}

@Composable
fun EventsScreen(viewModel: EventsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EventsContent(
        uiState = uiState,
    )
}

@Composable
fun EventsContent(
    uiState: EventsStates,

    ) {

    val context = LocalContext.current

    Column {
        if (uiState.isLoading) LoadingFullScreen()
        val totalElements = (uiState.events.size - 1)
        LazyColumn {
            uiState.events.forEachIndexed { i, event ->
                item {
                    EventCard(
                        event = event,
                        onEventClick = { },
                        onUserNameClick = { context.openBedRockActivity(NAV_EDIT_PROFILE) }
                    )
                }
            }
        }
    }
}