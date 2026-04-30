@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.createevent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.createevent.components.MyTimePicker
import com.mmfsin.oneworld.presentation.createevent.components.TimeClock

@Preview
@Composable
fun CreateEventScreenPV() {
    CreateEventContent(
        CreateEventStates(isLoading = false),
        {}, {}, {}, {}, {}, {},
    )
}

@Composable
fun CreateEventScreen(viewModel: CreateEventViewModel = hiltViewModel(), onBack: () -> Unit) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CreateEventContent(
        uiState = uiState,
        goBack = onBack,
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

    Column(Modifier.fillMaxSize().background(Color.White)) {
        Toolbar(iconVisible = true, R.string.create_event_toolbar) { goBack() }
        Column(Modifier.padding(horizontal = 16.dp).weight(1f).verticalScroll(rememberScrollState())) {
            Spacer(Modifier.height(24.dp))
            OutlinedTextField(
                value = uiState.title, onValueChange = { onTitleChange(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                label = { Text(text = stringResource(R.string.create_event_title)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.description, onValueChange = { onDescriptionChange(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                label = { Text(text = stringResource(R.string.create_event_description)) },
                maxLines = 12,
                minLines = 6,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
            Spacer(Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.create_event_time_picker),
                modifier = Modifier.clickable(onClick = { timePickerVisibility(true) }
                )
            )
            if (uiState.showTimePicker) {
                MyTimePicker(
                    onDismiss = { timePickerVisibility(false) },
                    onConfirm = { time -> setEventTime(time) }
                )
            }
            Spacer(Modifier.height(8.dp))
            uiState.time?.let { time -> TimeClock(time) { timePickerVisibility(true) } }

        }
        Box(
            Modifier.fillMaxWidth().background(Color.White).padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 24.dp
            )
        ) {
            Button(onClick = { createEvent() }) {
                Text(
                    text = stringResource(R.string.create_event_button).uppercase(),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

}