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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mmfsin.noexcusescompose.presentation.core.theme.BlueMedium
import com.mmfsin.noexcusescompose.presentation.core.theme.RedLight
import com.mmfsin.noexcusescompose.presentation.core.theme.White
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.EventCategory.Companion.getCategoryById
import com.mmfsin.oneworld.domain.models.getExampleEvents
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.utils.formatDateFromMillis
import com.mmfsin.oneworld.utils.openLink

@Preview
@Composable
fun EventCardPV() {
    EventCard(
        getExampleEvents().first(),
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
    ) {

        //        Box(modifier = Modifier.fillMaxWidth().height(6.dp).background(GrayHard))

        SpacerSmall()

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        ) {
            SmallText(text = "Creado por")
            SpacerMini(horizontal = true)
            SmallText(
                text = event.creatorName,
                color = BlueMedium,
                modifier = Modifier.clickable(onClick = { onUserNameClick() })
            )
        }

        SpacerSmall()

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

        Column(modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MediumText(text = event.likesCount.toString())
                SpacerMini(horizontal = true)
                Icon(
                    painterResource(R.drawable.ic_like_on), null,
                    modifier = Modifier.size(22.dp)
                )

                SpacerSmall(horizontal = true)

                MediumText(text = event.attendeesCount.toString())
                SpacerMini(horizontal = true)
                Icon(
                    painterResource(R.drawable.ic_assistant), null,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(Modifier.weight(1f))

                val category = getCategoryById(event.category)
                Row(
                    modifier = Modifier.clip(RoundedCornerShape(16.dp))
                        .background(category.color)
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(category.icon), null,
                        modifier = Modifier.size(16.dp)
                    )
                    SpacerSmall(horizontal = true)
                    SmallText(category.title)
                }
            }

            SpacerSmall()

            MediumText(
                text = event.title,
                fontWeight = FontWeight.SemiBold,
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

            SpacerSmall()

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SmallText(text = R.string.events_where)
                Spacer(Modifier.weight(1f))
                SmallText(text = event.address)
            }

            SpacerMini()

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SmallText(text = R.string.events_when)
                Spacer(Modifier.weight(1f))
                SmallText(text = event.date.formatDateFromMillis())
                SmallText(text = ",")
                SpacerMini(horizontal = true)
                SmallText(text = event.hour.toString())
                SmallText(text = ":")
                SmallText(text = event.minutes.toString())
            }

            SpacerLarge()
        }
    }
}