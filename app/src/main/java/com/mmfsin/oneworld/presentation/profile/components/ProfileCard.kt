package com.mmfsin.oneworld.presentation.profile.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.UserProfile
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerCustom
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.theme.BlueMedium
import com.mmfsin.oneworld.presentation.core.theme.GrayLight

@Preview
@Composable
fun ProfileCardPV() {
    ProfileCard(
        UserProfile(
            name = "Juanito Macandé",
            biography = "Cuando salga el Sol, me recordará cuando estés allí.",
            website = "www.estereotipia.com"
        )
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (userProfile.imageUrl != null) {
                AsyncImage(
                    model = userProfile.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).clip(CircleShape).background(GrayLight),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painterResource(R.drawable.gnome), null,
                    modifier = Modifier.size(56.dp).clip(CircleShape)
                )
            }

            SpacerCustom(space = 8.dp, horizontal = true)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("0", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    SmallText(R.string.profile_events)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("0", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    SmallText(R.string.profile_assisted)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("0", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    SmallText(R.string.profile_assisted)
                }
            }
        }

        SpacerSmall()

//        Text(
//            text = userProfile.name,
//            style = MaterialTheme.typography.titleLarge,
//            fontWeight = FontWeight.SemiBold
//        )

        userProfile.biography?.let { bio ->
            SpacerSmall()
            Text(text = bio)
        }

        userProfile.website?.let { web ->
            SpacerSmall()
            Text(
                text = web,
                fontWeight = FontWeight.SemiBold,
                color = BlueMedium,
                modifier = Modifier.clickable(
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, web.toUri())
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error abriendo enlace", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            )
        }
    }
}