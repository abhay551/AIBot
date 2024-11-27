package com.abhay.aibot

import aibot.composeapp.generated.resources.Res
import aibot.composeapp.generated.resources.ic_chat
import aibot.composeapp.generated.resources.ic_history
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhay.aibot.presentation.screens.chatscreen.ChatScreen
import com.abhay.aibot.presentation.screens.historyscreen.HistoryScreen
import com.abhay.aibot.presentation.screens.settingsscreen.SettingsScreen
import com.abhay.aibot.theme.AppTheme
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val isDarkTheme = remember { mutableStateOf(false) }
    AppTheme(
        isDarkTheme = isDarkTheme.value
    ) {
        Scaffold(
            bottomBar = { BottomBar(navController = navController) }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = AppRoutes.ChatScreen.routes) {
                composable(route = AppRoutes.ChatScreen.routes) {
                    ChatScreen(modifier = Modifier.padding(paddingValues))
                }
                composable(route = AppRoutes.HistoryScreen.routes) {
                    HistoryScreen(modifier = Modifier.padding(paddingValues))
                }
                composable(route = AppRoutes.SettingsScreen.routes) {
                    SettingsScreen(
                        modifier = Modifier.padding(paddingValues),
                        isDarkMode = isDarkTheme.value,
                        changeTheme = { isDarkTheme.value = it },
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == AppRoutes.ChatScreen.routes,
            onClick = { navController.navigate(AppRoutes.ChatScreen.routes) },
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_chat),
                    contentDescription = "Chat Icon",
                )
            }
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == AppRoutes.HistoryScreen.routes,
            onClick = { navController.navigate(AppRoutes.HistoryScreen.routes) },
            icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_history),
                    contentDescription = "History Icon",
                )
            }
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == AppRoutes.SettingsScreen.routes,
            onClick = { navController.navigate(AppRoutes.SettingsScreen.routes) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Icon",
                )
            }
        )
    }
}