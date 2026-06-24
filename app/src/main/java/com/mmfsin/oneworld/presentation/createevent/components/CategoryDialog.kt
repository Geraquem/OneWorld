package com.mmfsin.oneworld.presentation.createevent.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmfsin.oneworld.domain.models.EventType.Companion.getCategories
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.theme.GreenLight
import com.mmfsin.oneworld.presentation.core.theme.White

@Preview
@Composable
fun CategoryDialogPV() {
    CategoryDialog({}, 0, {})
}

@Composable
fun CategoryDialog(
    onDismiss: () -> Unit,
    actualCategory: Int,
    selected: (Int) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(White)
        ) {
            items(getCategories()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(if (it.id == actualCategory) GreenLight else White)
                        .clickable(onClick = { selected(it.id) })
                        .padding(16.dp),
                ) {
                    Image(painter = painterResource(it.icon), null)
                    SpacerMedium(horizontal = true)
                    Column() {
                        MediumText(text = it.title, fontWeight = FontWeight.SemiBold)
                        SpacerMini()
                        SmallText(text = it.description)
                    }
                }
            }
        }
    }
}