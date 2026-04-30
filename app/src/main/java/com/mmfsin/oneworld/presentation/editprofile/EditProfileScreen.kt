package com.mmfsin.oneworld.presentation.editprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.MyTextField
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.OrangeLight

@Preview
@Composable
fun EditProfileScreenPV() {
    EditProfileContent(
        EditProfileStates(),
        {}, {}, {}, {}, {})
}

@Composable
fun EditProfileScreen(viewModel: EditProfileViewModel = hiltViewModel(), onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EditProfileContent(
        uiState = uiState,
        goBack = onBack,
        changeName = { viewModel.changeName(it) },
        changeBio = { viewModel.changeBio(it) },
        changeWebsite = { viewModel.changeWebsite(it) },
        saveChanges = { viewModel.saveProfileChanges() },
    )
}

@Composable
fun EditProfileContent(
    uiState: EditProfileStates,
    goBack: () -> Unit,
    changeName: (String) -> Unit,
    changeBio: (String) -> Unit,
    changeWebsite: (String) -> Unit,
    saveChanges: () -> Unit
) {

    if (uiState.flowCompleted) goBack()

    Column(
        modifier = Modifier.fillMaxWidth().background(GrayLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Toolbar(iconVisible = true, R.string.profile_edit_profile) { goBack() }
        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = "",
                    contentDescription = null,
                    modifier = Modifier.size(100.dp).clip(CircleShape).background(GrayLight)
                        .border(2.dp, OrangeLight, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(12.dp))
                Image(
                    painterResource(R.drawable.ic_edit), null,
                    modifier = Modifier.size(48.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = CircleShape,
                            clip = false
                        ).clip(CircleShape).background(OrangeLight).padding(10.dp)
                )
            }
            Spacer(Modifier.height(24.dp))

            MyTextField(uiState.name, { changeName(it) }, R.string.edit_profile_name)

            Spacer(Modifier.height(16.dp))

            MyTextField(
                uiState.biography, { changeBio(it) },
                label = R.string.edit_profile_biography,
                minLines = 6,
                maxLines = 12,
                singleLine = false
            )

            Spacer(Modifier.height(16.dp))

            MyTextField(uiState.website, { changeWebsite(it) }, R.string.edit_profile_website)

            Spacer(Modifier.height(24.dp))
        }
        Box(
            Modifier.fillMaxWidth().background(Color.White).padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 24.dp
            )
        ) {
            Button(onClick = { saveChanges() }) {
                Text(
                    text = stringResource(R.string.edit_profile_save_data).uppercase(),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}