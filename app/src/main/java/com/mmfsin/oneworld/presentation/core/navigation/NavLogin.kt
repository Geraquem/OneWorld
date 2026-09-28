package com.mmfsin.oneworld.presentation.core.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.presentation.login.LoginScreen

@Composable
fun NavLogin() {
    val navController = rememberNavController()
    val activity = LocalActivity.current

    NavHost(
        startDestination = Login,
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<Login> {
            LoginScreen(
                goBack = { activity?.finish() }
            )
        }
    }
}