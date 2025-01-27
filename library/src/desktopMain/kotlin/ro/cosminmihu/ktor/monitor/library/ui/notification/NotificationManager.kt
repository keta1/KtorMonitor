package ro.cosminmihu.ktor.monitor.library.ui.notification

actual class NotificationManager {

    actual fun clear() {

    }

    actual fun notify(messages: List<String>) {
        // JVM doesn't support notifications.
    }
}