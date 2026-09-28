package com.mmfsin.oneworld.presentation.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.noexcusescompose.presentation.core.theme.Black
import com.mmfsin.noexcusescompose.presentation.core.theme.GrayLight
import com.mmfsin.noexcusescompose.presentation.core.theme.White
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.BigText
import com.mmfsin.oneworld.presentation.core.components.CustomToolbar
import com.mmfsin.oneworld.presentation.core.components.ErrorDialog
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.theme.montserrat_bold

@Preview
@Composable
fun LoginScreenPV() {
    LoginContent(
        uiStates = LoginStates(),
        {}, {}
    )
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val uiStates by viewModel.uiState.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> viewModel.doLogin(result) }

    LoginContent(
        uiStates = uiStates,
        goBack = { goBack() },
        login = {
            val intent = viewModel.signInWithGoogle()
            launcher.launch(intent)
        }
    )
}

@Composable
fun LoginContent(
    uiStates: LoginStates,
    goBack: () -> Unit,
    login: () -> Unit,
) {
    Scaffold(
        topBar = { CustomToolbar(goBack = { goBack() }) }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            LoginView(login = { login() })
        }
    }
    if (uiStates.userLogged) goBack()
    if (uiStates.sww) ErrorDialog(accept = { goBack() })
}

@Composable
fun LoginView(
    login: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(GrayLight)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        BigText(
            text = stringResource(R.string.app_name),
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold
        )

        SpacerLarge()

        Card(
            onClick = { login() },
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(R.drawable.ic_google), null)

                SpacerSmall(horizontal = true)

                MediumText(
                    text = stringResource(R.string.profile_initiate_session),
                    color = Black,
                    fontFamily = montserrat_bold,
                    allCaps = true
                )
            }
        }

        Spacer(Modifier.weight(1f))
    }
}