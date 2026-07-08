package com.mmfsin.oneworld.presentation.core.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.presentation.events.detail.EventDetailScreen
import kotlinx.serialization.Serializable

@Composable
fun NavEventDetail(eventId: String?) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = EventDetail(eventId)
    ) {
        composable<EventDetail> { EventDetailScreen() }
    }
}

/** SCREENS */
@Serializable
data class EventDetail(val eventId: String?)