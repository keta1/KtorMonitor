package ro.cosminmihu.ktor.monitor.notification

import platform.UserNotifications.*
import platform.darwin.NSObject

internal class NotificationDelegate : NSObject(), UNUserNotificationCenterDelegateProtocol {

    override fun userNotificationCenter(
        center: UNUserNotificationCenter,
        willPresentNotification: UNNotification,
        withCompletionHandler: (UNNotificationPresentationOptions) -> Unit
    ) {
        // This is called when a notification is about to be presented while the app is in the foreground.
        // Tell the system to display the alert, play the sound, and update the badge.
        withCompletionHandler(UNNotificationPresentationOptionAlert or UNNotificationPresentationOptionSound or UNNotificationPresentationOptionBadge)
    }

    override fun userNotificationCenter(
        center: UNUserNotificationCenter,
        didReceiveNotificationResponse: UNNotificationResponse,
        withCompletionHandler: () -> Unit
    ) {
        // Handle user interaction with the notification (e.g., tapping on it).
        println("User interacted with notification: ${didReceiveNotificationResponse.notification.request.identifier}")

        // Call the completion handler when you're done.
        withCompletionHandler()
    }
}
