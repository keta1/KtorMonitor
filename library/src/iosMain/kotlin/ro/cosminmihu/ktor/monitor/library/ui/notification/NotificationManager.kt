package ro.cosminmihu.ktor.monitor.library.ui.notification

import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNTimeIntervalNotificationTrigger
import platform.UserNotifications.UNUserNotificationCenter

private const val NOTIFICATION_ID = 1_000_0000 // TODO check chucker

actual class NotificationManager {

    actual fun clear() {

    }

    actual fun notify(messages: List<String>) {
        val body = messages.joinToString("\n") { it }

        val notificationContent = UNMutableNotificationContent().apply {
            setTitle(NOTIFICATION_TITLE)
            setBody(body)
//            setSound()
            setUserInfo(userInfo + emptyMap<String, String>())
        }
        val trigger =
            UNTimeIntervalNotificationTrigger.Companion.triggerWithTimeInterval(1.0, false)
        val notificationRequest = UNNotificationRequest.Companion.requestWithIdentifier(
            identifier = NOTIFICATION_ID.toString(),
            content = notificationContent,
            trigger = trigger
        )

        val notificationCenter = UNUserNotificationCenter()
        notificationCenter.addNotificationRequest(notificationRequest) { error ->
            error?.let { println("Error showing notification: $error") }
        }
    }
}