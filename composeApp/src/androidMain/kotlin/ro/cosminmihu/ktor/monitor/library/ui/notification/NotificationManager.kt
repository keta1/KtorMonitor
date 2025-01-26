package ro.cosminmihu.ktor.monitor.library.ui.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import org.koin.core.component.inject
import ro.cosminmihu.ktor.monitor.R
import ro.cosminmihu.ktor.monitor.library.di.LibraryKoinComponent
import ro.cosminmihu.ktor.monitor.library.ui.KtorMonitorActivity

private const val NOTIFICATION_CHANNEL_ID = "KtorMonitorNotificationChannel"
private const val NOTIFICATION_CHANNEL_NAME = "Ktor Monitor Notification Channel"
private const val NOTIFICATION_CHANNEL_DESCRIPTION = "Ktor Monitor Notification Channel"
private const val NOTIFICATION_ID = 1_000_0000 // TODO check chucker

actual class NotificationManager : LibraryKoinComponent {

    private val context: Context by inject()

    actual fun notify(messages: List<String>) {
        if (!isNotificationPermissionGranted(context)) {
            return
        }

        val notificationManager = ContextCompat
            .getSystemService<NotificationManager>(context, NotificationManager::class.java)
            ?: return

        createNotificationChannel(notificationManager)

        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(NOTIFICATION_TITLE)
            .setLocalOnly(true)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // TODO icon
            .setContentIntent(createPendingIntent())
//            .addAction(createClearAction()) TODO clean
            .setStyle(NotificationCompat.InboxStyle().also { style ->
                messages.forEach { style.addLine(it) }
            })
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = NOTIFICATION_CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createPendingIntent(): PendingIntent? {
        val intent = Intent(context, KtorMonitorActivity::class.java)

        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun isNotificationPermissionGranted(context: Context): Boolean = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }

        else -> {
            true
        }
    }
}