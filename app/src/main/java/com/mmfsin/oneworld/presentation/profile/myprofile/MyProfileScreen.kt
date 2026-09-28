package com.mmfsin.oneworld.presentation.profile.myprofile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.noexcusescompose.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.components.ErrorDialog
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.login.LoginView
import com.mmfsin.oneworld.presentation.profile.components.ProfileCard
import com.mmfsin.oneworld.utils.NAV_CREATE_EVENT
import com.mmfsin.oneworld.utils.NAV_EDIT_PROFILE
import com.mmfsin.oneworld.utils.openBedRockActivity
import com.mmfsin.oneworld.utils.openLink

@Preview(showBackground = true)
@Composable
fun ProfileScreenPV() {
    ProfileContent(
        MyProfileStates(
            isLoading = false,
            userProfile = null
        ),
        {}, {}, {}, {},
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
) {
    Column(
        Modifier.fillMaxSize()
            .background(GrayLight)
            .padding(12.dp)
    ) {
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
}