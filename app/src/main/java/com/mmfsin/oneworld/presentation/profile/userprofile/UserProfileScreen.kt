package com.mmfsin.oneworld.presentation.profile.userprofile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.domain.models.UserProfile

@Preview
@Composable
fun UserProfileScreenPV() {
    UserProfileContent(
        uiState = UserProfileStates(
            profile = UserProfile(

            )
        ),
    )
}

@Composable
fun UserProfileScreen(viewModel: UserProfileViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UserProfileContent(
        uiState = uiState
    )
}

@Composable
fun UserProfileContent(
    uiState: UserProfileStates,
) {
//    ProfileView(
//        profile = uiState.profile,
//        events = emptyList(),
//        editProfile = {},
//        createEvent = {}
//    )
}