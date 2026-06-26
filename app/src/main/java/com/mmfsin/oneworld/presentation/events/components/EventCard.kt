package com.mmfsin.oneworld.presentation.events.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.getExampleEvent
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.theme.BlueMedium
import com.mmfsin.oneworld.presentation.core.theme.RedLight
import com.mmfsin.oneworld.presentation.core.theme.White
import com.mmfsin.oneworld.utils.formatDateFromMillis
import com.mmfsin.oneworld.utils.openLink

@Preview
@Composable
fun EventCardPV() {
    EventCard(
        getExampleEvent(),
        {}, {},
    )
}

@Composable
fun EventCard(
    event: Event,
    onEventClick: () -> Unit,
    onUserNameClick: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .clickable(onClick = { onEventClick() })
            .background(White)
            .padding(bottom = 16.dp)
    ) {
        AsyncImage(
            model = event.image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(300.dp)
                .background(RedLight)
        )

        Column(modifier = Modifier.padding(16.dp)) {

            MediumText(
                text = event.title,
                fontWeight = FontWeight.SemiBold
            )

            event.description?.let { d ->
                SpacerMini()
                MediumText(text = if (d.length > 200) d.take(200) + "…" else d)
            }

            event.webUrl?.let { web ->
                SpacerMini()
                MediumText(
                    text = web,
                    color = BlueMedium,
                    modifier = Modifier.clickable(onClick = { context.openLink(web) })
                )
            }
            //
            //            SpacerMedium()
            //
            //            Box(
            //                modifier = Modifier.fillMaxWidth()
            //                    .height(1.dp)
            //                    .clip(RoundedCornerShape(8.dp))
            //                    .background(GrayHard)
            //                    .alpha(0.5f)
            //            )

            SpacerMedium()

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SmallText(text = "¿Dónde?")
                Spacer(Modifier.weight(1f))
                SmallText(text = event.address)
            }

            SpacerMini()

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SmallText(text = "¿Cuándo?")
                Spacer(Modifier.weight(1f))
                SmallText(text = event.date.formatDateFromMillis())
                SmallText(text = ",")
                SpacerMini(horizontal = true)
                SmallText(text = event.hour.toString())
                SmallText(text = ":")
                SmallText(text = event.minutes.toString())
            }

            SpacerMini()
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SmallText(text = "Creado por")
                Spacer(Modifier.weight(1f))
                SmallText(
                    text = event.creatorName,
                    color = BlueMedium,
                    modifier = Modifier.clickable(onClick = { onUserNameClick() })
                )
            }
        }
    }
}