package com.mmfsin.oneworld.presentation.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.presentation.events.detail.EventDetailScreen

@Composable
fun NavEventDetail(eventId: String?) {
    val navController = rememberNavController()

    NavHost(
        startDestination = EventDetail(eventId),
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<EventDetail> { EventDetailScreen() }
    }
}