package com.mmfsin.oneworld.presentation.createevent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.theme.White


@Preview
@Composable
fun MyCalendarPickerPV() {
    MyCalendarPicker({}, {})
}

@Composable
fun MyCalendarPicker(onDismiss: () -> Unit, onConfirm: (Long) -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        val datePickerState = rememberDatePickerState()

        Column(
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                .background(White)
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = White
                ),
            )

            Row(
                modifier = Modifier.align(Alignment.End).padding(end = 8.dp)
            ) {
                TextButton(onClick = { onDismiss() }) {
                    SmallText(
                        text = R.string.create_event_time_picker_cancel,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                SpacerMedium(horizontal = true)

                TextButton(onClick = {
                    if (datePickerState.selectedDateMillis != null) {
                        datePickerState.selectedDateMillis?.let { date -> onConfirm(date) }
                    } else onDismiss()
                }) {
                    SmallText(
                        text = R.string.create_event_time_picker_accept,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}