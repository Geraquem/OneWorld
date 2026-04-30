package com.mmfsin.oneworld.presentation.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mmfsin.oneworld.R

@Preview
@Composable
fun ToolbarPV() {
    Toolbar(true, R.string.app_name)
}

@Composable
fun Toolbar(iconVisible: Boolean = false, text: Int = R.string.empty, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .zIndex(1f)
            .shadow(
                elevation = 8.dp,
                clip = false
            ).background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp, top = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(16.dp))
            if (iconVisible) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.cd_arrow_back),
                    modifier = Modifier.size(30.dp).clickable(onClick = { onClick() })
                )
            }
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = if (iconVisible) 12.dp else 0.dp)
            )
        }
    }
}