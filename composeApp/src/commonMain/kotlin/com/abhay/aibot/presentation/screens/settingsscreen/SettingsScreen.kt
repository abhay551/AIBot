package com.abhay.aibot.presentation.screens.settingsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhay.aibot.component.AppBar
import com.abhay.aibot.presentation.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    changeTheme: (Boolean) -> Unit,
) {
    val settingsViewModel = koinViewModel<SettingsViewModel>()
    val state = settingsViewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        settingsViewModel.sendAction(SettingsAction.ChangeTheme(isDarkMode))
    }

    Scaffold(modifier = modifier, topBar = { AppBar(title = "Settings") }) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark theme",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body1
                )
                Switch(
                    checked = state.isDarkTheme,
                    onCheckedChange = { checked ->
                        changeTheme(checked)
                        settingsViewModel.sendAction(SettingsAction.ChangeTheme(checked))
                    },
                    enabled = !state.isClearing
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { settingsViewModel.sendAction(SettingsAction.ClearMessages) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Clear Messages Icon",
                    tint = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Clear all messages",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.body1
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Navigate Icon",
                    tint = MaterialTheme.colors.primary
                )
            }
            if (state.isClearing) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
            }
        }
    }
}