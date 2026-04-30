package com.mmfsin.oneworld.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.theme.GrayLight

@Preview
@Composable
fun ProfileCardPV() {
    ProfileCard(UserProfile(name = "Juanito Macandé", biography = "lñksjñldkfj ñlkjfñlksd"))
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .zIndex(1f)
            .shadow(
                elevation = 4.dp,
                clip = false
            ).background(Color.White)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = userProfile.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(64.dp).clip(CircleShape).background(GrayLight),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = userProfile.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(2.dp))
            if (userProfile.biography.isNotBlank()) {
                Text(text = userProfile.biography)
            }
        }
    }
}