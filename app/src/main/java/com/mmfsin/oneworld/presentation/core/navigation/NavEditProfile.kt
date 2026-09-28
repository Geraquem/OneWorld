package com.mmfsin.oneworld.presentation.core.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.presentation.aaaaa.AAAScreen
import com.mmfsin.oneworld.presentation.profile.editprofile.EditProfileScreen

@Composable
fun NavEditProfile() {
    val navController = rememberNavController()

    NavHost(
        startDestination = EditProfile,
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<EditProfile> {
            EditProfileScreen(
                navChangeImage = { navController.navigate(AAAScreen) })
        }

        composable<AAAScreen> {
            AAAScreen()
        }
    }
}