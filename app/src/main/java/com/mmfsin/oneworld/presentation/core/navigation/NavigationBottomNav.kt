package com.mmfsin.oneworld.presentation.core.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.aaaaa.AAAScreen
import com.mmfsin.oneworld.presentation.core.components.Toolbar
import com.mmfsin.oneworld.presentation.home.HomeScreen
import com.mmfsin.oneworld.presentation.profile.ProfileScreen
import com.mmfsin.oneworld.utils.BN_EDIT_ID
import com.mmfsin.oneworld.utils.BN_HOME_ID
import com.mmfsin.oneworld.utils.BN_PROFILE_ID

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem(id = BN_HOME_ID, name = stringResource(R.string.bottom_nav_home), icon = painterResource(R.drawable.ic_home)),
        BottomNavItem(id = BN_EDIT_ID, name = stringResource(R.string.bottom_nav_edit), icon = painterResource(R.drawable.ic_edit)),
        BottomNavItem(id = BN_PROFILE_ID, name = stringResource(R.string.bottom_nav_profile), icon = painterResource(R.drawable.ic_profile)),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    var toolbarTitle by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { toolbarTitle?.let { title -> Toolbar(text = title) } },
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
                        label = { Text(text = item.name) },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors()
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BN_HOME_ID,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BN_HOME_ID) {
                toolbarTitle = stringResource(R.string.events_toolbar)
                HomeScreen()
            }
            composable(route = BN_EDIT_ID) {
                toolbarTitle = stringResource(R.string.create_event_toolbar)
                AAAScreen()
            }
            composable(route = BN_PROFILE_ID) {
                toolbarTitle = null
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