package ro.cosminmihu.ktor.monitor.library.ui.notification

expect class NotificationManager() {

    fun notify(messages: List<String>)

    fun clear()
}