package com.mmfsin.oneworld.presentation.editprofile

import android.app.Activity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.ButtonCustom
import com.mmfsin.oneworld.presentation.core.components.DialogLoading
import com.mmfsin.oneworld.presentation.core.components.MyTextField
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.OrangeLight
import com.mmfsin.oneworld.presentation.core.theme.RedMedium
import com.mmfsin.oneworld.presentation.editprofile.components.CloseSessionDialog
import com.mmfsin.oneworld.utils.ImagePicker

@Preview
@Composable
fun EditProfileScreenPV() {
    EditProfileContent(
        EditProfileStates(
            isLoading = false,
        ),
        {}, {}, {}, {},
        {}, {}, {}
    )
}

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    navChangeImage: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EditProfileContent(
        uiState = uiState,
        changeName = { viewModel.changeName(it) },
        changeImage = { viewModel.changeImage(it) },
        changeBio = { viewModel.changeBio(it) },
        changeWebsite = { viewModel.changeWebsite(it) },
        saveChanges = { viewModel.saveProfileChanges() },
        showCloseSessionDialog = { value -> viewModel.showCloseSessionDialog(value) },
        closeSession = { viewModel.closeSession() },
    )
}

@Composable
fun EditProfileContent(
    uiState: EditProfileStates,
    changeName: (String) -> Unit,
    changeImage: (String) -> Unit,
    changeBio: (String) -> Unit,
    changeWebsite: (String) -> Unit,
    saveChanges: () -> Unit,
    showCloseSessionDialog: (Boolean) -> Unit,
    closeSession: () -> Unit
) {

    val context = LocalContext.current
    val activity = context as Activity

    if (uiState.flowCompleted) activity.finish()

    Scaffold(
        topBar = {
            Toolbar(
                stringResource(R.string.profile_edit_profile),
                iconBackVisible = true,
                onBackClick = { activity.finish() }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(innerPadding)
                .background(GrayLight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SpacerLarge()

                if (uiState.imageUrl != null) {
                    AsyncImage(
                        model = uiState.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp).clip(CircleShape).background(GrayLight)
                            .border(2.dp, OrangeLight, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painterResource(R.drawable.gnome), null,
                        modifier = Modifier.size(120.dp).clip(CircleShape)
                    )
                }

                ImagePicker(onImageSelected = { changeImage(it.toString()) })

                SpacerSmall()

                MyTextField(
                    uiState.name, { changeName(it) },
                    R.string.edit_profile_name,
                    maxLength = 20
                )

                SpacerSmall()

                MyTextField(
                    uiState.biography ?: "", { changeBio(it) },
                    label = R.string.edit_profile_biography,
                    minLines = 1,
                    maxLines = 4,
                    maxLength = 100,
                    singleLine = false,
                    imeAction = ImeAction.None
                )

                SpacerSmall()

                MyTextField(
                    uiState.website ?: "", { changeWebsite(it) },
                    label = R.string.edit_profile_website,
                    imeAction = ImeAction.Done
                )

                Spacer(Modifier.height(24.dp))
            }
            Box(
                Modifier.fillMaxWidth().background(GrayLight)
                    .padding(16.dp)
                    .padding(bottom = 8.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    IconButton(onClick = { showCloseSessionDialog(true) }) {
                        Icon(
                            painterResource(R.drawable.ic_log_out), null,
                            tint = RedMedium
                        )
                    }

                    SpacerMedium(horizontal = true)

                    ButtonCustom(
                        onClick = { saveChanges() },
                        text = R.string.edit_profile_save_data,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.name.isNotBlank(),
                        textModifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
        if (uiState.showCloseSessionDialog) {
            CloseSessionDialog(
                onDismiss = { showCloseSessionDialog(false) },
                closeSession = { closeSession() }
            )
        }

        if (uiState.isLoading) DialogLoading(text = R.string.edit_profile_updating)
    }
}