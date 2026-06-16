package com.mmfsin.oneworld.presentation.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpacerCustom(space: Dp, horizontal: Boolean = false) =
    Spacer(if (horizontal) Modifier.width(space) else Modifier.height(space))

@Composable
fun SpacerMini(vertical: Boolean = true) =
    Spacer(if (vertical) Modifier.height(4.dp) else Modifier.width(4.dp))

@Composable
fun SpacerSmall(vertical: Boolean = true) =
    Spacer(if (vertical) Modifier.height(8.dp) else Modifier.width(8.dp))

@Composable
fun SpacerMedium(vertical: Boolean = true) =
    Spacer(if (vertical) Modifier.height(16.dp) else Modifier.width(16.dp))

@Composable
fun SpacerLarge(vertical: Boolean = true) =
    Spacer(if (vertical) Modifier.height(24.dp) else Modifier.width(24.dp))
