package ro.cosminmihu.ktor.monitor.ui.notification

internal expect class NotificationManager() {

    fun notify(messages: List<String>)

    fun clear()
}