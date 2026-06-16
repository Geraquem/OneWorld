package com.mmfsin.oneworld.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.ButtonCustom
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerCustom
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.theme.Black
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.GrayMedium
import com.mmfsin.oneworld.presentation.home.EventCard

@Preview
@Composable
fun ProfileViewPV() {
    ProfileView(
        profile = UserProfile(
            name = "Juanito",
            biography = "jcskljfdclkñszdjfñnzsdmgjm"
        ),
        events = emptyList(),
        editProfile = {},
        createEvent = {}
    )
}

@Composable
fun ProfileView(
    profile: UserProfile?,
    events: List<Event>?,
    editProfile: () -> Unit,
    createEvent: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(GrayLight)
    ) {

        profile?.let { ProfileCard(profile) }

        Column(Modifier.padding(horizontal = 16.dp)) {

            SpacerSmall()

            UserProfileButtons(editProfile = { editProfile() }, createEvent = { createEvent() })

            SpacerSmall()

            events?.let { e ->
                val totalEvents = e.size

                if (totalEvents < 1) SmallText(R.string.profile_created_events_none)
                else {
                    Text(
                        text = stringResource(R.string.profile_created_events, totalEvents.toString()),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                SpacerMedium()

                LazyColumn {
                    events.forEachIndexed { i, event ->
                        item { EventCard(event, i != (events.size - 1)) }
                    }
                }
            } ?: run { LoadingEvents() }
        }
    }
}

@Composable
fun UserProfileButtons(editProfile: () -> Unit, createEvent: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ButtonCustom(
            onClick = { editProfile() },
            text = R.string.profile_edit_profile,
            color = GrayMedium,
            textColor = Black,
            modifier = Modifier.weight(1f)
        )

        SpacerCustom(space = 8.dp, horizontal = true)

        ButtonCustom(
            onClick = { editProfile() },
            text = R.string.profile_create_event,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LoadingEvents() {
    SmallText(
        text = R.string.profile_loading_events,
        modifier = Modifier.alpha(0.5f),
    )
}