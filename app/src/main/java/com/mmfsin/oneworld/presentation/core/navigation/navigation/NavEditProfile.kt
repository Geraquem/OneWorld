package com.mmfsin.oneworld.presentation.core.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.presentation.aaaaa.AAAScreen
import com.mmfsin.oneworld.presentation.editprofile.EditProfileScreen
import kotlinx.serialization.Serializable

@Composable
fun NavEditProfile() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = EditProfile
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

/** SCREENS */
@Serializable
object EditProfile

@Serializable
object AAAScreen