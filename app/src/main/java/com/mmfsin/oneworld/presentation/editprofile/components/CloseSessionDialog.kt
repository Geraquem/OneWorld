package com.mmfsin.oneworld.presentation.editprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.theme.OrangeHard
import com.mmfsin.oneworld.presentation.core.theme.OrangeMedium
import com.mmfsin.oneworld.presentation.core.theme.White

@Preview
@Composable
fun CloseSessionDialogPV() {
    CloseSessionDialog({}, {})
}

@Composable
fun CloseSessionDialog(
    onDismiss: () -> Unit,
    closeSession: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(White)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().background(OrangeMedium).padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(R.drawable.ic_log_out), null,
                    tint = White
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp, bottom = 8.dp)
            ) {
                MediumText(text = R.string.close_session_title)

                SpacerSmall()

                MediumText(text = R.string.close_session_description)

                SpacerLarge()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        MediumText(
                            text = R.string.close_session_btn_cancel,
                        )
                    }

                    SpacerMedium(horizontal = true)

                    TextButton(onClick = { closeSession() }) {
                        MediumText(
                            text = R.string.close_session_btn_yes,
                            color = OrangeHard,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}