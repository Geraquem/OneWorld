package com.mmfsin.oneworld.presentation.createevent.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.oneworld.presentation.core.theme.OrangeLight

@Preview(showBackground = true)
@Composable
fun TimeClockPV() {
    TimeClock(Pair("12", "43"))
}

@Composable
fun TimeClock(time: Pair<String, String>, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = { onClick() }
        )
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(OrangeLight).padding(12.dp)
        ) {
            Text(text = time.first, style = MaterialTheme.typography.titleLarge)
        }

        Text(text = ":", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 4.dp))

        Box(
            modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(OrangeLight).padding(12.dp)
        ) {
            Text(text = time.second, style = MaterialTheme.typography.titleLarge)
        }
    }
}