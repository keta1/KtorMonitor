package ro.cosminmihu.ktor.monitor.notification

 public actual class NotificationManager {
     public actual suspend fun clear() {
        // JVM doesn't support notifications.
    }

     public actual suspend fun notify(messages: List<String>) {
        // JVM doesn't support notifications.
    }
}