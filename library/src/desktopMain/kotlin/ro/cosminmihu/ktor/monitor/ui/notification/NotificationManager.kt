package ro.cosminmihu.ktor.monitor.ui.notification

internal actual class NotificationManager {

    actual suspend fun clear() {
    }

    actual suspend fun notify(messages: List<String>) {
        // JVM doesn't support notifications.
    }
}