package com.mmfsin.oneworld.presentation.profile

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.ErrorDialog
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.profile.components.LoginScreen
import com.mmfsin.oneworld.presentation.profile.components.ProfileView
import com.mmfsin.oneworld.utils.CREATE_EVENT
import com.mmfsin.oneworld.utils.EDIT_PROFILE
import com.mmfsin.oneworld.utils.openBedRockActivity

@Preview(showBackground = true)
@Composable
fun ProfileScreenPV() {
    ProfileContent(
        ProfileStates(
            isLoading = false,
            userLogged = true,
            userProfile = UserProfile(
                name = "Juan"
            )
        ),
        {}, { Intent() })
}

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel(), toolbarTitle: (String) -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    toolbarTitle(uiState.userProfile?.name ?: "")

    ProfileContent(
        uiState = uiState,
        doLogin = { viewModel.doLogin(it) },
        signInWithGoogle = { viewModel.signInWithGoogle() }
    )
}

@Composable
fun ProfileContent(
    uiState: ProfileStates,
    doLogin: (ActivityResult) -> Unit,
    signInWithGoogle: () -> Intent
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> doLogin(result) }

    if (uiState.userLogged && uiState.userProfile != null) {
        ProfileView(
            profile = uiState.userProfile,
            events = uiState.eventsCreated,
            editProfile = { context.openBedRockActivity(EDIT_PROFILE) },
            createEvent = { context.openBedRockActivity(CREATE_EVENT) }
        )
    } else {
        LoginScreen(
            initiateSession = {
                val intent = signInWithGoogle()
                launcher.launch(intent)
            }
        )
        return
    }

    if (uiState.isLoading) LoadingFullScreen()
    if (uiState.sww) ErrorDialog(accept = {})
}