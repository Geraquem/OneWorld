package com.mmfsin.oneworld.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.OrangeMedium
import com.mmfsin.oneworld.presentation.events.components.EventCard

@Preview
@Composable
fun ProfileViewPV() {
    ProfileView(
        profile = UserProfile(
            name = "Juanito Macandé",
            biography = "jcskljfdclkñszdjfñnzsdmgjm",
            website = "estereotipia.com"
        ),
        events = null,
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
        modifier = Modifier.fillMaxSize().background(GrayLight).padding(horizontal = 16.dp)
    ) {
        profile?.let {
            ProfileCard(
                userProfile = profile,
                editProfile = { editProfile() }
            )
        }

        SpacerMini()

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (events == null) {
                MediumText(text = R.string.profile_loading_events)
            } else {
                MediumText(text = stringResource(R.string.profile_created_events, (events.size).toString()))
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(onClick = { createEvent() }) {
                MediumText(
                    text = R.string.create_event_button,
                    color = OrangeMedium
                )
            }
        }

        events?.let { e ->
            LazyColumn {
                e.forEachIndexed { i, event ->
                    item {
//                        EventCard(event, i != (events.size - 1))
                    }
                }
            }
        }
    }
}