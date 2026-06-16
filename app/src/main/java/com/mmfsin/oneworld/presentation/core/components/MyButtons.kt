package com.mmfsin.oneworld.presentation.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.theme.OrangeMedium
import com.mmfsin.oneworld.presentation.core.theme.White

@Preview
@Composable
fun ButtonCustomPV() {
    Column() {
        ButtonCustom(
            onClick = {},
            text = R.string.app_name
        )

        OutlinedButtonCustom(
            onClick = {},
            text = R.string.app_name
        )
    }
}

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: Int,
    enabled: Boolean = true,
    color: Color = OrangeMedium,
    textColor: Color = White
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        MediumText(
            text = text,
            color = textColor,
            modifier = textModifier
        )
    }
}

@Composable
fun OutlinedButtonCustom(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: Int,
    enabled: Boolean = true,
    textColor: Color = OrangeMedium
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(1.dp, textColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        MediumText(
            text = text,
            color = textColor
        )
    }
}