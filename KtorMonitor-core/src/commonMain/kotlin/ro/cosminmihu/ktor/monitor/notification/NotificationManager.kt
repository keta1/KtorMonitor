package ro.cosminmihu.ktor.monitor.notification

public expect class NotificationManager() {
    public suspend fun notify(messages: List<String>)

    public suspend fun clear()
}
