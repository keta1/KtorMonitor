package ro.cosminmihu.ktor.monitor.ui.notification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import platform.UserNotifications.UNUserNotificationCenter
import ro.cosminmihu.ktor.monitor.ui.Dimens
import ro.cosminmihu.ktor.monitor.ui.resources.Res
import ro.cosminmihu.ktor.monitor.ui.resources.ktor_permission_message

@Composable
internal actual fun NotificationPermissionBanner() {
    val coroutineScope = rememberCoroutineScope()
    var isGranted by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        isGranted = UNUserNotificationCenter.currentNotificationCenter().isNotificationPermissionGranted()
    }

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
        }
    }
}