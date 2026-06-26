package com.mmfsin.oneworld.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.BigText
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerCustom
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.theme.BlueMedium
import com.mmfsin.oneworld.presentation.core.theme.GrayLight
import com.mmfsin.oneworld.presentation.core.theme.White
import com.mmfsin.oneworld.utils.openLink

@Preview
@Composable
fun ProfileCardPV() {
    ProfileCard(
        UserProfile(
            name = "Juanito Macandé",
            biography = "Cuando salga el Sol, me recordará cuando estés allí.",
            website = "www.estereotipia.com"
        ),
        {}
    )
}

@Composable
fun ProfileCard(
    userProfile: UserProfile,
    editProfile: () -> Unit
) {

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {

        SpacerSmall()

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BigText(
                userProfile.name,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { editProfile() }) {
                Icon(painterResource(R.drawable.ic_settings), null)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (userProfile.imageUrl != null) {
                        AsyncImage(
                            model = userProfile.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.size(72.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(GrayLight),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painterResource(R.drawable.gnome), null,
                            modifier = Modifier.size(72.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    SpacerCustom(space = 8.dp, horizontal = true)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            BigText("0", fontWeight = FontWeight.SemiBold)
                            SmallText(R.string.profile_events)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            BigText("0", fontWeight = FontWeight.SemiBold)
                            SmallText(R.string.profile_assisted)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            BigText("0", fontWeight = FontWeight.SemiBold)
                            SmallText(R.string.profile_assisted)
                        }
                    }
                }

                SpacerSmall()

                userProfile.biography?.let { bio ->
                    SpacerSmall()
                    SmallText(text = bio)
                }

                userProfile.website?.let { web ->
                    SpacerSmall()
                    SmallText(
                        text = web,
                        fontWeight = FontWeight.SemiBold,
                        color = BlueMedium,
                        modifier = Modifier.clickable(
                            onClick = { context.openLink(web) }
                        )
                    )
                }
            }
        }
    }
}