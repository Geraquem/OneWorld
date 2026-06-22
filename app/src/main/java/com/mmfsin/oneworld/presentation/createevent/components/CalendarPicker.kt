package com.mmfsin.oneworld.presentation.createevent.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog


@Preview
@Composable
fun MyCalendarPickerPV() {
    MyCalendarPicker({}, {})
}

@Composable
fun MyCalendarPicker(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        val datePickerState = rememberDatePickerState()

        DatePicker(
            state = datePickerState
        )
    }
}