@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.createevent.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mmfsin.oneworld.R
import java.util.Locale

@Composable
fun MyTimePicker(
    onDismiss: () -> Unit,
    onConfirm: (Pair<String, String>) -> Unit,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = 14,
        initialMinute = 50,
        is24Hour = true
    )
    TimePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val hour = "%02d".format(Locale.US, timePickerState.hour)
                val minute = "%02d".format(Locale.US, timePickerState.minute)

                onConfirm(Pair(hour, minute))
            }) {
                Text(text = stringResource(R.string.create_event_time_picker_confirm))
            }
        },
        title = { }
    ) { TimePicker(state = timePickerState) }
}