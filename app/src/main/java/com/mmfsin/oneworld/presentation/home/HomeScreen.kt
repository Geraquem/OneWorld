package com.mmfsin.oneworld.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.domain.models.getExampleEvent
import com.mmfsin.oneworld.presentation.core.components.LoadingFullScreen
import com.mmfsin.oneworld.presentation.core.theme.GrayMedium

@Preview
@Composable
fun HomeScreenPV() {
    //    HomeScreen()
    EventCard(getExampleEvent(), true)
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val totalElements = (uiState.events.size - 1)
    Column {
        if (uiState.isLoading) LoadingFullScreen()
        LazyColumn {
            uiState.events.forEachIndexed { i, event -> item { EventCard(event, i != totalElements) } }
        }
    }
}

@Composable
fun EventCard(event: Event, showDivider: Boolean) {
    Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        AsyncImage(
            model = event.image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )
        Spacer(Modifier.height(12.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = event.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(6.dp))
            event.description?.let { d->
                Text(text = d, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(Modifier.height(12.dp))
        }
        if (showDivider) Spacer(Modifier.fillMaxWidth().height(16.dp).background(GrayMedium))
    }
}