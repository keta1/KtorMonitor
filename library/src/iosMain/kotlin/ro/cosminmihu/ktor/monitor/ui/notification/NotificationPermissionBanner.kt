package ro.cosminmihu.ktor.monitor.ui.notification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import platform.UserNotifications.UNUserNotificationCenter
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_permission_message
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_permission_grant

@Composable
internal actual fun NotificationPermissionBanner() {
    val coroutineScope = rememberCoroutineScope()
    var isGranted by remember { mutableStateOf(true) }

    AnimatedVisibility(visible = !isGranted) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(vertical = Dimens.Small, horizontal = Dimens.Medium)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.ktor_permission_message),
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        isGranted = UNUserNotificationCenter.currentNotificationCenter().isNotificationPermissionGranted()
                    }
                }
            ) {
                Text(text = stringResource(Res.string.ktor_permission_grant))
            }
        }
    }
}