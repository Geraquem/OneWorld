@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.createevent.components.CategoryDialog
import com.mmfsin.oneworld.presentation.events.components.EventCard
import com.mmfsin.oneworld.presentation.events.components.EventsToolbar
import com.mmfsin.oneworld.utils.NAV_EVENT_DETAIL
import com.mmfsin.oneworld.utils.NAV_USER_PROFILE
import com.mmfsin.oneworld.utils.openBedRockActivity

@Preview
@Composable
fun EventsScreenPV() {
    Column() {
        EventsContent(
            uiState = EventsStates(
                events = emptyList(),
                isLoading = false
            )
        )
    }
}

@Composable
fun EventsScreen(
    viewModel: EventsViewModel = hiltViewModel(),
    toolbar: (@Composable () -> Unit) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    toolbar {
        EventsToolbar(
            uiState.searchingCategory,
            changeCategory = { viewModel.categoryDialogVisibility(true) }
        )
    }

    EventsContent(
        uiState = uiState,
    )

    if (uiState.categoryDialogVisibility) {
        CategoryDialog(
            onDismiss = { viewModel.categoryDialogVisibility(false) },
            actualCategory = uiState.searchingCategory,
            selected = { newCategory -> viewModel.updateSearchingCategory(newCategory) }
        )
    }
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
                        onEventClick = { eventId ->
                            context.openBedRockActivity(
                                navGraph = NAV_EVENT_DETAIL,
                                strArgs = eventId
                            )
                        },
                        onUserNameClick = { context.openBedRockActivity(NAV_USER_PROFILE) }
                    )
                }
            }
        }
    }
}