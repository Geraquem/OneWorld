@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.noexcusescompose.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.createevent.components.CategoryDialog
import com.mmfsin.oneworld.presentation.events.components.EventCard
import com.mmfsin.oneworld.presentation.events.components.EventsToolbar
import com.mmfsin.oneworld.utils.NAV_EVENT_DETAIL
import com.mmfsin.oneworld.utils.NAV_USER_PROFILE
import com.mmfsin.oneworld.utils.openBedRockActivity

@Preview
@Composable
fun EventsScreenPV() {
    EventsContent(
        uiState = EventsStates(
            events = emptyList(),
            //            events = getExampleEvents(),
            isLoading = false
        ),
        {}, {}, {}, {},
    )
}

@Composable
fun EventsScreen(
    viewModel: EventsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    EventsContent(
        uiState = uiState,
        showCategoryDialog = { viewModel.showCategoryDialog(it) },
        updateSearchingCategory = { viewModel.updateSearchingCategory(it) },

        goToEventDetail = { context.openBedRockActivity(navGraph = NAV_EVENT_DETAIL, it) },
        goToUserProfile = { context.openBedRockActivity(navGraph = NAV_USER_PROFILE, it) },
    )
}

@Composable
fun EventsContent(
    uiState: EventsStates,
    showCategoryDialog: (Boolean) -> Unit,
    updateSearchingCategory: (Int) -> Unit,

    goToEventDetail: (String) -> Unit,
    goToUserProfile: (String) -> Unit,
) {

    Column(Modifier.fillMaxSize().background(GrayLight)) {
        val totalElements = (uiState.events.size - 1)

        EventsToolbar(
            uiState.searchingCategory,
            changeCategory = { showCategoryDialog(true) }
        )

        if (uiState.events.isEmpty()) EmptyEvents()
        else {
            LazyColumn(
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(
                    items = uiState.events,
                    key = { event -> event.id }
                ) { event ->
                    EventCard(
                        event = event,
                        onEventClick = { goToEventDetail(event.id) },
                        onUserNameClick = { goToUserProfile(event.creatorId) }
                    )
                }
            }
        }
    }

    if (uiState.showCategoryDialog) {
        CategoryDialog(
            onDismiss = { showCategoryDialog(false) },
            actualCategory = uiState.searchingCategory,
            selected = { newCategory -> updateSearchingCategory(newCategory) }
        )
    }

    if (uiState.isLoading) LoadingFullScreen()
}

@Composable
fun EmptyEvents() {
    Column(
        Modifier.fillMaxWidth().alpha(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerLarge()
        SmallText(text = R.string.events_empty)
        SpacerSmall()
        Icon(painterResource(R.drawable.ic_sad_face), null)
    }
}