package com.mmfsin.oneworld.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import com.mmfsin.oneworld.presentation.core.components.MiniLoading
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerMedium
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.home.EventCard

@Preview
@Composable
fun ProfileViewPV() {
    ProfileView(
        profile = UserProfile(
            name = "Juanito",
            biography = "jcskljfdclkñszdjfñnzsdmgjm"
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

    val totalEvents = events?.size?.toString() ?: ""

    Column(
        modifier = Modifier.fillMaxSize().background(GrayLight)
    ) {

        profile?.let { ProfileCard(profile) }

        Column(Modifier.padding(horizontal = 16.dp)) {

            SpacerMedium()

            UserProfileButtons(editProfile = { editProfile() }, createEvent = { createEvent() })

            events?.let { e ->

                Text(
                    text = stringResource(R.string.profile_created_events, totalEvents),
                    fontWeight = FontWeight.SemiBold
                )

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
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = { editProfile() }, modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.profile_edit_profile),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        /*
        Spacer(Modifier.width(8.dp))

        Button(onClick = { createEvent() }, modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.profile_create_event),
                style = MaterialTheme.typography.bodyLarge
            )
        }

         */
    }
}

@Composable
fun LoadingEvents() {
    SmallText(
        text = R.string.profile_loading_events,
        modifier = Modifier.alpha(0.5f),
    )
}