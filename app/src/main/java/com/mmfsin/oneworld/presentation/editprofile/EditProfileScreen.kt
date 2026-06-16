package com.mmfsin.oneworld.presentation.editprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.ButtonCustom
import com.mmfsin.oneworld.presentation.core.components.DialogLoading
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.MyTextField
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.OrangeLight
import com.mmfsin.oneworld.presentation.core.theme.OrangeMedium

@Preview
@Composable
fun EditProfileScreenPV() {
    EditProfileContent(
        EditProfileStates(
            isLoading = false,
        ),
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

            SpacerLarge()

            if (uiState.imageUrl != null) {
                AsyncImage(
                    model = uiState.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp).clip(CircleShape).background(GrayLight)
                        .border(2.dp, OrangeLight, CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painterResource(R.drawable.gnome), null,
                    modifier = Modifier.size(100.dp).clip(CircleShape)
                )
            }

            SpacerSmall()

            TextButton(onClick = {}) {
                MediumText(
                    R.string.edit_profile_image,
                    color = OrangeMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            SpacerSmall()

            MyTextField(uiState.name, { changeName(it) }, R.string.edit_profile_name)

            SpacerSmall()

            MyTextField(
                uiState.biography ?: "", { changeBio(it) },
                label = R.string.edit_profile_biography,
                minLines = 6,
                maxLines = 12,
                singleLine = false,
                imeAction = ImeAction.None
            )

            SpacerSmall()

            MyTextField(
                uiState.website ?: "", { changeWebsite(it) },
                label = R.string.edit_profile_website
            )

            Spacer(Modifier.height(24.dp))
        }
        Box(
            Modifier.fillMaxWidth().background(GrayLight)
                .padding(16.dp)
                .padding(bottom = 8.dp)
        ) {
            ButtonCustom(
                onClick = { saveChanges() },
                text = R.string.edit_profile_save_data,
                modifier = Modifier.fillMaxWidth(),
                textModifier = Modifier.padding(4.dp)
            )
        }
    }
    if (uiState.isLoading) DialogLoading(text = R.string.edit_profile_updating)
}