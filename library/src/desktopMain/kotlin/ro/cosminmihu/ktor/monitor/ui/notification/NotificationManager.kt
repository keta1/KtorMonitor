package ro.cosminmihu.ktor.monitor.ui.notification

internal actual class NotificationManager {

    actual fun clear() {
    }

    actual fun notify(messages: List<String>) {
        // JVM doesn't support notifications.
    }
}