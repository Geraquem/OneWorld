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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.White
import com.mmfsin.oneworld.presentation.createevent.components.MyCalendarPicker
import com.mmfsin.oneworld.presentation.createevent.components.MyTimePicker

@Preview
@Composable
fun CreateEventScreenPV() {
    CreateEventContent(
        CreateEventStates(
            isLoading = false,
            time = Pair("12", "45"),
            date = "14 de diciembre de 2025",
            webUrl = "ñadjkñlakd"
        ),
        {}, {}, {}, {},
        {}, {}, {}, {},
        {},
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
        onWebUrlChange = { viewModel.onWebUrlChange(it) },
        timePickerVisibility = { viewModel.timePickerVisibility(it) },
        datePickerVisibility = { viewModel.datePickerVisibility(it) },
        setEventTime = { viewModel.setEventTime(it) },
        setEventDate = { viewModel.setEventDate(it) },
        createEvent = { viewModel.createEvent() }
    )
}

@Composable
fun CreateEventContent(
    uiState: CreateEventStates,
    goBack: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onWebUrlChange: (String) -> Unit,
    timePickerVisibility: (Boolean) -> Unit,
    datePickerVisibility: (Boolean) -> Unit,
    setEventTime: (Pair<String, String>) -> Unit,
    setEventDate: (String) -> Unit,
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
                imeAction = ImeAction.Done
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

            MyWhiteTextField(
                value = uiState.webUrl, onValueChange = { onWebUrlChange(it) },
                label = R.string.create_event_web,
                imeAction = ImeAction.Done,
                maxLength = 50
            )

            SpacerSmall()

            SmallText(text = R.string.create_event_time_date)
            SpacerMini()
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { datePickerVisibility(true) }) {
                    Icon(painterResource(R.drawable.ic_calendar), null)
                }

                SpacerMedium(horizontal = true)

                uiState.date?.let { date ->
                    Text(text = date, style = MaterialTheme.typography.bodyLarge)
                }
            }

            SpacerMedium()

            SmallText(text = R.string.create_event_time_picker)
            SpacerMini()
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(White),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = { timePickerVisibility(true) }) {
                    Icon(painterResource(R.drawable.ic_clock), null)
                }

                SpacerMedium(horizontal = true)

                uiState.time?.let { time ->
                    Text(text = time.first, style = MaterialTheme.typography.bodyLarge)
                    Text(text = ":", style = MaterialTheme.typography.bodyLarge)
                    Text(text = time.second, style = MaterialTheme.typography.bodyLarge)
                }
            }


            Spacer(Modifier.weight(1f))

            ButtonCustom(
                onClick = { createEvent() },
                modifier = Modifier.fillMaxWidth(),
                text = R.string.create_event_button
            )

            SpacerLarge()
        }
    }

    if (uiState.showDatePicker) {
        MyCalendarPicker(
            onDismiss = { timePickerVisibility(false) },
            onConfirm = { date -> setEventDate(date) }
        )
    }

    if (uiState.showTimePicker) {
        MyTimePicker(
            onDismiss = { timePickerVisibility(false) },
            onConfirm = { time -> setEventTime(time) }
        )
    }
}