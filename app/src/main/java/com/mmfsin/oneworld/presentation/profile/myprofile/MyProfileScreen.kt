package com.mmfsin.oneworld.presentation.profile.myprofile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.noexcusescompose.presentation.core.theme.GrayLight
import com.mmfsin.noexcusescompose.presentation.core.theme.OrangeMedium
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.ErrorDialog
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.events.components.EventCard
import com.mmfsin.oneworld.presentation.login.LoginView
import com.mmfsin.oneworld.presentation.profile.components.ProfileCard
import com.mmfsin.oneworld.utils.NAV_CREATE_EVENT
import com.mmfsin.oneworld.utils.NAV_EDIT_PROFILE
import com.mmfsin.oneworld.utils.NAV_EVENT_DETAIL
import com.mmfsin.oneworld.utils.openBedRockActivity
import com.mmfsin.oneworld.utils.openLink

@Preview(showBackground = true)
@Composable
fun ProfileScreenPV() {
    ProfileContent(
        MyProfileStates(
            isLoading = false,
            userProfile = UserProfile(
                name = "Juanito",
            ),
            eventsCreated = emptyList()
        ),
        {}, {}, {}, {},
        {},
    )
}

@Composable
fun ProfileScreen(viewModel: MyProfileViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> viewModel.doLogin(result) }

    if (uiState.userProfile == null) {
        LoginView(
            login = {
                val intent = viewModel.signInWithGoogle()
                launcher.launch(intent)
            }
        )
    } else {
        ProfileContent(
            uiState = uiState,
            goToEditProfile = { context.openBedRockActivity(NAV_EDIT_PROFILE) },
            openLink = { context.openLink(it) },
            openImage = {},
            createEvent = { context.openBedRockActivity(NAV_CREATE_EVENT) },
            goToEventDetail = { context.openBedRockActivity(NAV_EVENT_DETAIL, it) },
        )
    }

    if (uiState.isLoading) LoadingFullScreen()
    if (uiState.sww) ErrorDialog(accept = {})
}

@Composable
fun ProfileContent(
    uiState: MyProfileStates,
    goToEditProfile: (String) -> Unit,
    openLink: (String?) -> Unit,
    openImage: (String?) -> Unit,
    createEvent: () -> Unit,
    goToEventDetail: (String) -> Unit
) {
    CompositionLocalProvider(
        LocalOverscrollFactory provides null
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .background(GrayLight),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                if (uiState.userProfile != null) {
                    ProfileCard(
                        userProfile = uiState.userProfile,
                        isMyProfile = true,
                        editProfile = { goToEditProfile(uiState.userProfile.id) },
                        openLink = { openLink(uiState.userProfile.website) },
                        openImage = { openImage(uiState.userProfile.imageUrl) }
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (uiState.eventsCreated == null) {
                        MediumText(text = R.string.profile_loading_events)
                    } else {
                        MediumText(
                            text = stringResource(
                                R.string.profile_created_events,
                                (uiState.eventsCreated.size).toString()
                            )
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(onClick = { createEvent() }) {
                        MediumText(
                            text = R.string.create_event_button,
                            color = OrangeMedium
                        )
                    }
                }
            }

            uiState.eventsCreated?.let { events ->
                items(
                    items = events,
                    key = { event -> event.id }
                ) { event ->
                    EventCard(
                        event = event,
                        onEventClick = { goToEventDetail(event.id) },
                        onUserNameClick = { }
                    )
                }
            }
        }
    }
}