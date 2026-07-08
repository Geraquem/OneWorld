@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.events.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.EventCategory.Companion.getCategoryById
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.theme.BlueMedium
import com.mmfsin.oneworld.presentation.core.theme.White

@Preview
@Composable
fun EventsToolbarPV() {
    EventsToolbar(1, {})
}

@Composable
fun EventsToolbar(categoryId: Int, changeCategory: () -> Unit) {
    val category = getCategoryById(categoryId)

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White
        ),

        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MediumText(text = R.string.events_toolbar, fontWeight = FontWeight.SemiBold)
                SpacerMini(horizontal = true)
                MediumText(
                    text = category.title, color = BlueMedium,
                    modifier = Modifier.weight(1f).clickable(onClick = { changeCategory() })
                )
                IconButton(onClick = {}) {
                    Icon(painterResource(R.drawable.ic_profile), null)
                }
            }
        }
    )
}
