package com.mmfsin.oneworld.presentation.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.oneworld.R

@Preview(showBackground = true)
@Composable
fun MyTextFieldPV() {
    MyTextField("Texto de prueba", {}, R.string.app_name)
}

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: Int,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value, onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(
                text = stringResource(label),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.bodyLarge,
        minLines = minLines,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}