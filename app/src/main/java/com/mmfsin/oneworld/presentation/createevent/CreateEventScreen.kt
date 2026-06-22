@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.createevent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.ButtonCustom
import com.mmfsin.oneworld.presentation.core.components.MyWhiteTextField
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.OrangeMedium
import com.mmfsin.oneworld.presentation.core.theme.White
import com.mmfsin.oneworld.presentation.createevent.components.MyTimePicker

@Preview
@Composable
fun CreateEventScreenPV() {
    CreateEventContent(
        CreateEventStates(
            isLoading = false,
            time = Pair("12", "45"),
            date = "14 de diciembre de 2025"
        ),
        {}, {}, {},
        {}, {}, {},
    )
}

@Composable
fun CreateEventScreen(viewModel: CreateEventViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateEventContent(
        uiState = uiState,
        goBack = { },
        onTitleChange = { viewModel.onTitleChange(it) },
        onDescriptionChange = { viewModel.onDescriptionChange(it) },
        timePickerVisibility = { viewModel.timePickerVisibility(it) },
        setEventTime = { viewModel.setEventTime(it) },
        createEvent = { viewModel.createEvent() }
    )
}

@Composable
fun CreateEventContent(
    uiState: CreateEventStates,
    goBack: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    timePickerVisibility: (Boolean) -> Unit,
    setEventTime: (Pair<String, String>) -> Unit,
    createEvent: () -> Unit,
) {

    if (uiState.closeAndGoBack) goBack()

    Scaffold(
        topBar = { Toolbar(text = stringResource(R.string.create_event_toolbar)) }
    ) { innerPadding ->

        Column(
            Modifier.fillMaxSize()
                .background(GrayLight)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            MyWhiteTextField(
                value = uiState.title, onValueChange = { onTitleChange(it) },
                label = R.string.create_event_title,
                imeAction = ImeAction.Next
            )

            SpacerSmall()

            MyWhiteTextField(
                value = uiState.description, onValueChange = { onDescriptionChange(it) },
                label = R.string.create_event_description,
                imeAction = ImeAction.None,
                singleLine = false,
                maxLines = 15,
                maxLength = 500
            )

            SpacerSmall()

            Row(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { timePickerVisibility(true) }) {
                    SmallText(
                        text = R.string.create_event_time_picker,
                        fontWeight = FontWeight.SemiBold,
                        color = OrangeMedium
                    )
                }

                SpacerSmall(horizontal = true)

                uiState.time?.let { time ->
                    Text(text = time.first, style = MaterialTheme.typography.bodyLarge)
                    Text(text = ":", style = MaterialTheme.typography.bodyLarge)
                    Text(text = time.second, style = MaterialTheme.typography.bodyLarge)
                }
            }

            SpacerSmall()

            Row(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { timePickerVisibility(true) }) {
                    SmallText(
                        text = R.string.create_event_time_date,
                        fontWeight = FontWeight.SemiBold,
                        color = OrangeMedium
                    )
                }

                uiState.date?.let { date ->
                    Text(text = date, style = MaterialTheme.typography.bodyLarge)
                }
            }



            if (uiState.showTimePicker) {
                MyTimePicker(
                    onDismiss = { timePickerVisibility(false) },
                    onConfirm = { time -> setEventTime(time) }
                )
            }
            Spacer(Modifier.height(8.dp))

            Spacer(Modifier.weight(1f))

            ButtonCustom(
                onClick = { createEvent() },
                modifier = Modifier.fillMaxWidth(),
                text = R.string.create_event_button
            )

            SpacerLarge()
        }
    }
}