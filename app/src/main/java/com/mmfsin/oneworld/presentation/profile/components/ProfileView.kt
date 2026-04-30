package com.mmfsin.oneworld.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.MiniLoading
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
        events = emptyList(),
        editProfile = {},
        createEvent = {}
    )
}

@Composable
fun ProfileView(profile: UserProfile?, events: List<Event>?, editProfile: () -> Unit, createEvent: () -> Unit) {

    val totalEvents = events?.size?.toString() ?: ""

    Column(
        modifier = Modifier.fillMaxSize().background(GrayLight)
    ) {

        profile?.let { ProfileCard(profile) }

        Column(Modifier.padding(horizontal = 16.dp)) {

            Spacer(Modifier.height(12.dp))

            UserProfileButtons(editProfile = { editProfile() }, createEvent = { createEvent() })

            Text(
                text = stringResource(R.string.profile_created_events, totalEvents),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(24.dp))
            if (events == null) MiniLoading()
            else {
                if (events.isEmpty()) EmptyEvents()
                else {
                    LazyColumn {
                        events.forEachIndexed { i, event ->
                            item { EventCard(event, i != (events.size - 1)) }
                        }
                    }
                }
            }
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
        Spacer(Modifier.width(8.dp))
        Button(onClick = { createEvent() }, modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.profile_create_event),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun EmptyEvents() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp).alpha(0.75f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.ic_crying), null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            stringResource(R.string.profile_no_events),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}