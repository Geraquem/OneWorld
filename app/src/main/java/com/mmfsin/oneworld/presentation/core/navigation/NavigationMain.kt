package com.mmfsin.oneworld.presentation.core.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.aaaaa.AAAScreen
import com.mmfsin.oneworld.presentation.core.components.CustomMainToolbar
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.events.EventsScreen
import com.mmfsin.oneworld.presentation.main.MainViewModel
import com.mmfsin.oneworld.presentation.profile.myprofile.ProfileScreen
import com.mmfsin.oneworld.utils.BN_EDIT_ID
import com.mmfsin.oneworld.utils.BN_EVENTS_ID
import com.mmfsin.oneworld.utils.BN_PROFILE_ID

@Composable
fun NavigationMain(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val uiStates by viewModel.uiState.collectAsStateWithLifecycle()

    val bottomNavItems = listOf(
        BottomNavItem(id = BN_EVENTS_ID, name = stringResource(R.string.bottom_nav_events), icon = painterResource(R.drawable.ic_home)),
        BottomNavItem(id = BN_EDIT_ID, name = stringResource(R.string.bottom_nav_edit), icon = painterResource(R.drawable.ic_edit)),
        BottomNavItem(id = BN_PROFILE_ID, name = stringResource(R.string.bottom_nav_profile), icon = painterResource(R.drawable.ic_profile)),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = { CustomMainToolbar() },
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination == item.id,
                        onClick = {
                            navController.navigate(item.id) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(painter = item.icon, contentDescription = item.name) },
                        label = { MediumText(text = item.name) },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors()
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BN_EVENTS_ID,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BN_EVENTS_ID) {
                EventsScreen()
            }
            composable(route = BN_EDIT_ID) {
                AAAScreen()
            }
            composable(route = BN_PROFILE_ID) {
                ProfileScreen()
            }
        }
    }
}

data class BottomNavItem(
    val id: String,
    val name: String,
    val icon: Painter,
)