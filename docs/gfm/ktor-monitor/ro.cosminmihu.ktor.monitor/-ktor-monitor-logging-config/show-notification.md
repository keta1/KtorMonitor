//[ktor-monitor](../../../index.md)/[ro.cosminmihu.ktor.monitor](../index.md)/[KtorMonitorLoggingConfig](index.md)/[showNotification](show-notification.md)

# showNotification

[common]\
var [showNotification](show-notification.md): [Boolean](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/-boolean/index.html)

Keep track of latest requests and responses into notification. By default:

- 
   android   - enabled. android.permission.POST_NOTIFICATIONS needs to be granted.
- 
   ios       - enabled. Notifications permission needs to be granted.
- 
   desktop   - not supported.