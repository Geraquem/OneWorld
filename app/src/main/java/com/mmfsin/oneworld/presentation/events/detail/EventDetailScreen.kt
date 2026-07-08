package com.mmfsin.oneworld.presentation.events.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.domain.models.getExampleEvent
import com.mmfsin.oneworld.presentation.core.components.ButtonCustom
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.components.SmallText
import com.mmfsin.oneworld.presentation.core.components.SpacerLarge
import com.mmfsin.oneworld.presentation.core.components.SpacerMini
import com.mmfsin.oneworld.presentation.core.components.SpacerSmall
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.core.theme.BlueMedium
import com.mmfsin.oneworld.presentation.core.theme.RedLight
import com.mmfsin.oneworld.utils.formatDateFromMillis
import com.mmfsin.oneworld.utils.openLink

@Preview
@Composable
fun EventDetailPV() {
    EventDetailContent(
        uiState = EventDetailStates(
            event = getExampleEvent()
        )
    )
}

@Composable
fun EventDetailScreen(viewModel: EventDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    EventDetailContent(
        uiState = uiState
    )
}

@Composable
fun EventDetailContent(
    uiState: EventDetailStates,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Toolbar(
                iconBackVisible = true,
                onBackClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            uiState.event?.let { e ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(300.dp)
                        .background(RedLight)
                )

                Column(Modifier.padding(horizontal = 16.dp)) {
                    SpacerSmall()
                    MediumText(text = e.title, fontWeight = FontWeight.SemiBold)

                    e.description?.let { desc ->
                        SpacerSmall()
                        MediumText(text = desc)
                    }

                    e.webUrl?.let { web ->
                        SpacerLarge()
                        SmallText(text = "Más información en:")
                        MediumText(
                            text = web,
                            color = BlueMedium,
                            modifier = Modifier.clickable(onClick = { context.openLink(web) })
                        )
                    }

                    SpacerSmall()

                    SmallText(text = R.string.events_location)
                    SpacerMini()
                    MediumText(
                        text = e.address,
                        fontWeight = FontWeight.SemiBold
                    )

                    SpacerSmall()

                    SmallText(text = R.string.events_date_and_hour)
                    SpacerMini()
                    Row() {
                        MediumText(text = e.date.formatDateFromMillis(), fontWeight = FontWeight.SemiBold)
                        MediumText(text = ",", fontWeight = FontWeight.SemiBold)
                        SpacerSmall(horizontal = true)
                        MediumText(text = e.hour.toString(), fontWeight = FontWeight.SemiBold)
                        MediumText(text = ":", fontWeight = FontWeight.SemiBold)
                        MediumText(text = e.minutes.toString(), fontWeight = FontWeight.SemiBold)
                    }

                    SpacerLarge()

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        MediumText(text = "Asistentes:")
                        SpacerMini(horizontal = true)
                        MediumText(text = "684", fontWeight = FontWeight.SemiBold)
                        SpacerMini(horizontal = true)
                        Icon(
                            painterResource(R.drawable.ic_profile), null,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    SpacerMini()
                    ButtonCustom(
                        onClick = {},
                        text = R.string.app_name,
                        modifier = Modifier.fillMaxWidth()
                    )

                    //                    SpacerSmall()
                    //                    MediumText(text = "Evento creado por")
                    //                    MediumText(text = e.creatorName)


                    SpacerLarge()
                }
            }
        }
    }
}