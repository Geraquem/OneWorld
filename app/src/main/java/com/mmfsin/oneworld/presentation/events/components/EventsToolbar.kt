@file:OptIn(ExperimentalMaterial3Api::class)

package com.mmfsin.oneworld.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.noexcusescompose.presentation.core.theme.BlueMedium
import com.mmfsin.noexcusescompose.presentation.core.theme.White
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.EventCategory.Companion.getCategoryById
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall

@Preview
@Composable
fun EventsToolbarPV() {
    EventsToolbar(1, {})
}

@Composable
fun EventsToolbar(
    categoryId: Int,
    changeCategory: () -> Unit
) {
    val category = getCategoryById(categoryId)

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(White)
            .clickable(onClick = { changeCategory() })
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MediumText(text = R.string.events_toolbar, fontWeight = FontWeight.SemiBold)

        SpacerSmall(horizontal = true)

        Icon(
            painterResource(category.icon), null,
            tint = BlueMedium
        )

        SpacerMini(horizontal = true)

        MediumText(
            text = category.title,
            color = BlueMedium,
            modifier = Modifier.weight(1f)
        )
    }
}
