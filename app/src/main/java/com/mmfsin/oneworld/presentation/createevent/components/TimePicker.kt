@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.createevent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import java.util.Locale

@Preview
@Composable
fun MyTimePickerPV() {
    MyTimePicker({}, { })
}

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
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(GrayLight)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        ) {
            SmallText(text = R.string.create_event_time_picker)
            SpacerSmall()
            TimeInput(state = timePickerState)
            Row(
                modifier = Modifier.align(Alignment.End)
            ) {
                TextButton(onClick = { onDismiss() }) {
                    SmallText(
                        text = R.string.create_event_time_pickera_cancel,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                TextButton(onClick = {
                    val hour = "%02d".format(Locale.US, timePickerState.hour)
                    val minute = "%02d".format(Locale.US, timePickerState.minute)

                    onConfirm(Pair(hour, minute))
                }) {
                    SmallText(
                        text = R.string.create_event_time_pickera_accept,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}