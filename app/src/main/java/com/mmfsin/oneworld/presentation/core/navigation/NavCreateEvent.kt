package com.mmfsin.oneworld.presentation.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.presentation.createevent.CreateEventScreen

@Composable
fun NavCreateEvent() {
    val navController = rememberNavController()

    NavHost(
        startDestination = CreateEvent,
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<CreateEvent> {
            CreateEventScreen()
        }
    }
}